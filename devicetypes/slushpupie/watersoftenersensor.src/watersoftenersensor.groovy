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
        attribute  "distance", number
    }

    simulator {
    }

    // Preferences
    preferences {
        input "ip", "text", title: "Arduino IP Address", description: "IP Address in form 192.168.1.226", required: true, displayDuringSetup: true
        input "port", "text", title: "Arduino Port", description: "port in form of 8090", required: true, displayDuringSetup: true
        input "mac", "text", title: "Arduino MAC Addr", description: "MAC Address in form of 02A1B2C3D4E5", required: true, displayDuringSetup: true
        input "refill_level", "number", title: "Refill Distance", description: "Distance to alert when needing refill", defaultValue: 75, required: true, displayDuringSetup: true
    }

    // Tile Definitions
    tiles (scale: 2){
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 3, height: 2) {
            state "default", label:'Refresh', action: "refresh.refresh", icon: "st.secondary.refresh-icon"
        }

        standardTile("configure", "device.configure", inactiveLabel: false, decoration: "flat", width: 3, height: 2) {
            state "configure", label:'Configure', action:"configuration.configure", icon:"st.secondary.tools"
        }

    }
}

// parse events into attributes
def parse(String description) {
    //log.debug "Parsing '${description}'"
    def msg = parseLanMessage(description)
    def headerString = msg.header

    if (!headerString) {
        //log.debug "headerstring was null for some reason :("
    }

    def payload = msg.json

    if (payload) {
        log.debug "Payload: $payload"


        def results = []

        results << createEvent(name: 'distance', value: payload.distance)

        if (payload.distance > settings.refill_level) {
            results << createEvent(name: 'consumableStatus', value: "replace")
        } else {
            results << createEvent(name: 'consumableStatus', value: "good")
        }


        log.debug results
        return results
    }
}

private getHostAddress() {
    def ip = settings.ip
    def port = settings.port

    log.debug "Using ip: ${ip} and port: ${port} for device: ${device.id}"
    return ip + ":" + port
}

def sendEthernet() {
    log.debug "Executing 'sendEthernet' ${message}"
    if (settings.ip != null && settings.port != null) {
        sendHubCommand(new physicalgraph.device.HubAction(
                method: "GET",
                path: "/distance.json",
                headers: [ HOST: "${getHostAddress()}" ]
        ))
    }
    else {
        state.alertMessage = "WaterSoftenerSensor Parent Device has not yet been fully configured. Click the 'Gear' icon, enter data for all fields, and click 'Done'"
        runIn(2, "sendAlert")
    }
}

def configure() {
    log.debug "Executing 'configure()'"
    updateDeviceNetworkID()
    sendEvent(name: "refill_level", value: refill_level)
}

def refresh() {
    log.debug "Executing 'refresh()'"
    sendEthernet()
    sendEvent(name: "refill_level", value: refill_level)
}

def installed() {
    log.debug "Executing 'installed()'"
    if ( device.deviceNetworkId =~ /^[A-Z0-9]{12}$/)
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
        sendEvent(name: "refill_level", value: refill_level)
    }
    else {
//		log.trace "updated(): Ran within last 5 seconds so aborting."
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