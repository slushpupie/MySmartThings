/**
 *
 *  Copyright 2017 Jay Kline <jay@slushpupie.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
    definition (name: "WaterSoftenerSensor", namespace: "slushpupie", author: "Jay Kline") {
        capability "Sensor"
        capability "Consumable"
        capability "Polling"
        capability "Refresh"

        attribute  "distance", "number"
        attribute  "percentConsumed", "number"
        attribute  "lastMeasured", "date"
    }

    // Preferences
    preferences {
        input "ip", "text", title: "Arduino IP Address", description: "IP Address in form 192.168.1.226", required: true, displayDuringSetup: true
        input "port", "text", title: "Arduino Port", description: "port in form of 8090", required: true, displayDuringSetup: true
        input "mac", "text", title: "Arduino MAC Addr", description: "MAC Address in form of 02A1B2C3D4E5", required: true, displayDuringSetup: true
        input "refill_level", "number", title: "Refill Distance", description: "Distance to alert when needing refill", defaultValue: 80, required: true, displayDuringSetup: true
        input "full_level", "number", title: "Full Distance", description: "Distance to that indicates full", defaultValue: 15, required: true, displayDuringSetup: true
    }

    simulator {
      reply "distance": "{\"distance\": 70.00, \"updated\": 1497150183}"
    }

    // Tile Definitions
    tiles (scale: 2){
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "default", label:'Refresh', action: "refresh.refresh", icon: "st.secondary.refresh-icon"
        }

        standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "configure", label:'Configure', action:"configuration.configure", icon:"st.secondary.tools"
        }

        valueTile("distance", "device.distance", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
          state "default", label: '${currentValue}cm'
        }
        
        valueTile("lastMeasured", "device.lastMeasured", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
          state "default", label: 'Last measured at ${currentValue}'
        }
        
        valueTile("percentConsumed", "device.percentConsumed", inactiveLabel: false, width: 2, height: 2) {
          state "default", label: '${currentValue}%', backgroundColors: [
          	    [value: 10, color: "#e86d13"],
                [value: 90, color: "#00a0dc"]
          ]
        }

        valueTile("consumed", "device.consumableStatus", width: 2, height: 2) {
          state "good", label: "Filled", backgroundColor: "#00a0dc"
          state "replace", label: "Needs refill", backgroundColor: "#e86d13"
        }
        
        multiAttributeTile(name: "composite", type: "generic", width: 4, height: 4) {
            tileAttribute("device.percentConsumed", key: "PRIMARY_CONTROL") {
            	attributeState "default", label: '${currentValue}%', backgroundColors: [
                    [value: 10, color: "#e86d13"],
                    [value: 90, color: "#00a0dc"]
          		]
                }
            tileAttribute("device.consumableStatus", key: "SECONDARY_CONTROL") {
            	attributeState "good", label: "Filled", backgroundColor: "#00a0dc"
         		attributeState "replace", label: "Needs refill", backgroundColor: "#e86d13"
            }

        }

        
        
        
        main('consumed')
        details(['composite','distance','refresh','configure'])
    }
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
    def msg = parseLanMessage(description)
    def headerString = msg.header

    if (!headerString) {
        //log.debug "headerstring was null for some reason :("
    }



    def payload = msg.json
    
    return parsePayload(payload)
}


def parsePayload(payload) {
    if (payload) {
        log.debug "Payload: $payload"

        def events = []

        events << createEvent(name: 'distance', value: payload.distance)
        events << createEvent(name: 'lastMeasured', value: new java.util.Date((payload.updated as long)*1000L))
        /* Calculate percentage consumed
         * examples:
         *   cv = 45, full = 15, empty = 75;  100 - (45-15)/(75-15) = (30/60) = 50%
         *   cv = 17, 100 - (17-15)/(75-15) = (2/60) = 100 - 3.33% = 96.66%
         *   cv = 10, 100 - (10-15)/(75-15) = 100 - (-5/60) = 100 - -8.33% = 108.33%
         *   cv = 80, 100 - (80-15)/(75-15) = 100 - (65/60) = -8.33%
         */
        def consumed = 100 * (1 - (payload.distance - settings.full_level) / (settings.refill_level - settings.full_level))
        events << createEvent(name: 'percentConsumed', value: consumed.setScale(1, BigDecimal.ROUND_HALF_UP))

        if (payload.distance > settings.refill_level) {
            events << createEvent(name: 'consumableStatus', value: "replace")
        } else {
            events << createEvent(name: 'consumableStatus', value: "good")
        }

        log.debug events
        return events
    }
}

private getHostAddress() {
    def ip = settings.ip
    def port = settings.port

    //log.debug "Using ip: ${ip} and port: ${port} for device: ${device.id}"
    return ip + ":" + port
}

private String getCallBackAddress() {
    return device.hub.getDataValue("localIP") + ":" + device.hub.getDataValue("localSrvPortTCP")
}

def new_poll() {
    log.debug "Executing 'poll()'"
    if (settings.ip != null && settings.port != null) {
        def events = []
        //try {
        	httpGet("http://${getHostAddress()}/distance.json") { resp ->                
                events << createEvent(name: 'distance', value: resp.distance)
		        events << createEvent(name: 'lastMeasured', value: new java.util.Date((long)resp.updated*1000))
                def consumed = 100 * (1 - (payload.distance - settings.full_level) / (settings.refill_level - settings.full_level))
                events << createEvent(name: 'percentConsumed', value: consumed)

                if (payload.distance > settings.refill_level) {
                    events << createEvent(name: 'consumableStatus', value: "replace")
                } else {
                    events << createEvent(name: 'consumableStatus', value: "good")
                }
            }
         //} catch (Throwable e) {
         //	log.debug(e)
         //}
         return events
    }
}

def poll() {
    log.debug "Executing 'poll'"
    if (settings.ip != null && settings.port != null) {
        setDeviceNetworkId(settings.ip,settings.port)  
        log.debug "taking HubAction"
        try {
            def hubAction = new physicalgraph.device.HubAction(
                    method: "GET",
                    path: "/distance.json",
                    headers: [ HOST: "${getHostAddress()}" ]
            )
            log.debug "HubAction ${hubAction}"
            def result = sendHubCommand(hubAction)
        	return hubAction
        } catch (Exception e) {
        	log.debug e
        }
    }
    else {
        state.alertMessage = "WaterSoftenerSensor Device has not yet been fully configured. Click the 'Gear' icon, enter data for all fields, and click 'Done'"
    }
}

def configure() {
    log.debug "Executing 'configure()'"
    updateDeviceNetworkID()
}

def refresh() {
    log.debug "Executing 'refresh()'"
    return poll()
}

def installed() {
    log.debug "Executing 'installed()'"
    //if ( device.deviceNetworkId =~ /^[A-Z0-9]{12}$/)
    if ( device.deviceNetworkId =~ /^.{8,52}$/)
    {
    }
    else
    {
        state.alertMessage = "WaterSoftenerSensor Device has not yet been fully configured. Click the 'Gear' icon, enter data for all fields, and click 'Done'"
    }
}

def initialize() {
    log.debug "Executing 'initialize()'"
    sendEvent(name: "refill_level", value: refill_level)
}
def updated() {
    if (!state.updatedLastRanAt || now() >= state.updatedLastRanAt + 5000) {
        state.updatedLastRanAt = now()
        log.debug "Executing 'updated()'"
        runIn(3, "updateDeviceNetworkID")
    }
    else {
		    log.trace "updated(): Ran within last 5 seconds so aborting."
    }
}


def updateDeviceNetworkID() {
    log.debug "Executing 'updateDeviceNetworkID'"
    if(device.deviceNetworkId!=mac) {
        log.debug "setting deviceNetworkID = ${mac}"
        device.setDeviceNetworkId("${mac}")
    }
    refresh()
}

private String convertIPtoHex(ipAddress) { 
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
    return hex

}

private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
    return hexport
}

private setDeviceNetworkId(ip,port){
  	def iphex = convertIPtoHex(ip)
  	def porthex = convertPortToHex(port)
  	device.deviceNetworkId = "$iphex:$porthex"
  	log.debug "Device Network Id set to ${iphex}:${porthex}"
}