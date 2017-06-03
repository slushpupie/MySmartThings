/**
 *  Web Services Tutorial
 *
 *  Copyright 2015 SmartThings
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
definition(
    name: "Web Stats",
    namespace: "slushpupie",
    author: "Slushpupie",
    description: "Web Stats endpoint",
    category: "",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    oauth: [displayName: "Web stats", displayLink: "http://localhost:4567"])


preferences {
  section ("Allow external service to gather data") {
    input "sensors", "capability.sensor", multiple: true, required: false
    input "temperatureMeasurements", "capability.temperatureMeasurement", multiple: true, required: false
    input "thermostats", "capability.thermostat", multiple: true, required: false
    input "energyMeters", "capability.energyMeters", multiple: true, required: false
  }
}

mappings {
  path("/sensors") {
   action: [
     GET: "listAllSensors"
   ]
  }
  path("/all") {
    action: [
      GET: "listAll"
    ]
  }
  path("/themostats") {
    action: [
      GET: "listTemperatures"
    ]
  }
  path("/temperatures") {
    action: [
      GET: "listThermostats"
    ]
  }
  path("/energyMeters") {
    action: [
      GET: "listEnergyMeters"
    ]
  }
}

def listAllSensors() {
  def resp = []
  sensors.each {
    resp << [id: it.id,
             //name: it.displayName,
             caps: it.supportedAttributes,
            ]
   }
   return resp
}
def listAll() {
    def resp = []
    resp << [temperature: listTemperatures()]
    resp << [thermostat: listThermostats()]
    resp << [energyMeters: listEnergyMeters()]
    
    return resp
}

def listTemperatures() {
	def resp = []
    temperatureMeasurements.each {
        resp << [id: it.id,
                 name: it.displayName, 
        		 temp: it.currentTemperature,
                ]
    }
    return resp
}

def listThermostats() {
	def resp = []
    thermostats.each {
        resp << [id: it.id,
                 name: it.displayName, 
        		 coolingSetpoint: it.currentCoolingSetpoint,
                 heatingSetpoint: it.currentHeatingSetpoing,
                 temp: it.currentTemperature,
                 fanMode: it.currentThermostatFanMode,
                 mode: it.currentThermostatMode,
                 state: it.currentThemostatOperatingState,
                 setpoint: it.currentSetpoint,
                ]
    }
    return resp
}

def listEnergyMeters() {
	def resp = []
    energyrMeters.each {
        resp << [id: it.id,
                 //name: it.displayName, 
        		 temp: it.currentEnergy,
                ]
    }
    return resp
}

def installed() {}

def updated() {}