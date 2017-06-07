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
  	input "accelerationSensors", "capability.accelerationSensor", title: "accelerationSensor", multiple: true, required: false
    input "actuators", "capability.actuator", title: "actuator", multiple: true, required: false
    input "alarms", "capability.alarm", title: "alarm", multiple: true, required: false
    input "audioNotifications", "capability.audioNotification", title: "audioNotification", multiple: true, required: false
    input "batterys", "capability.battery", title: "battery", multiple: true, required: false
    input "beacons", "capability.beacon", title: "beacon", multiple: true, required: false
    input "bridges", "capability.bridge", title: "bridge", multiple: true, required: false
    input "bulbs", "capability.bulb", title: "bulb", multiple: true, required: false
    input "buttons", "capability.button", title: "button", multiple: true, required: false
    input "carbonDioxideMeasurements", "capability.carbonDioxideMeasurement", title: "carbonDioxideMeasurement", multiple: true, required: false
    input "carbonMonoxideDetectors", "capability.carbonMonoxideDetector", title: "carbonMonoxideDetector", multiple: true, required: false
    input "colorControls", "capability.colorControl", title: "colorControl", multiple: true, required: false
    input "colorTemperatures", "capability.colorTemperature", title: "colorTemperature", multiple: true, required: false
    input "configurations", "capability.configuration", title: "configuration", multiple: true, required: false
    input "consumables", "capability.consumable", title: "consumable", multiple: true, required: false
    input "contactSensors", "capability.contactSensor", title: "contactSensor", multiple: true, required: false
    input "doorControls", "capability.doorControl", title: "doorControl", multiple: true, required: false
    input "energyMeters", "capability.energyMeter", title: "energyMeter", multiple: true, required: false
    input "estimatedTimeOfArrivals", "capability.estimatedTimeOfArrival", title: "estimatedTimeOfArrival", multiple: true, required: false
    input "garageDoorControls", "capability.garageDoorControl", title: "garageDoorControl", multiple: true, required: false
    input "holdableButtons", "capability.holdableButton", title: "holdableButton", multiple: true, required: false
    input "illuminanceMeasurements", "capability.illuminanceMeasurement", title: "illuminanceMeasurement", multiple: true, required: false
    input "imageCaptures", "capability.imageCapture", title: "imageCapture", multiple: true, required: false
    input "indicators", "capability.indicator", title: "indicator", multiple: true, required: false
    input "infraredLevels", "capability.infraredLevel", title: "infraredLevel", multiple: true, required: false
    input "lights", "capability.light", title: "light", multiple: true, required: false
    input "locks", "capability.lock", title: "lock", multiple: true, required: false
    input "lockOnlys", "capability.lockOnly", title: "lockOnly", multiple: true, required: false
    input "mediaControllers", "capability.mediaController", title: "mediaController", multiple: true, required: false
    input "momentarys", "capability.momentary", title: "momentary", multiple: true, required: false
    input "motionSensors", "capability.motionSensor", title: "motionSensor", multiple: true, required: false
    input "musicPlayers", "capability.musicPlayer", title: "musicPlayer", multiple: true, required: false
    input "notifications", "capability.notification", title: "notification", multiple: true, required: false
    input "outlets", "capability.outlet", title: "outlet", multiple: true, required: false
    input "phMeasurements", "capability.phMeasurement", title: "phMeasurement", multiple: true, required: false
    input "pollings", "capability.polling", title: "polling", multiple: true, required: false
    input "powerMeters", "capability.powerMeter", title: "powerMeter", multiple: true, required: false
    input "powerSources", "capability.powerSource", title: "powerSource", multiple: true, required: false
    input "presenceSensors", "capability.presenceSensor", title: "presenceSensor", multiple: true, required: false
    input "refreshs", "capability.refresh", title: "refresh", multiple: true, required: false
    input "relativeHumidityMeasurements", "capability.relativeHumidityMeasurement", title: "relativeHumidityMeasurement", multiple: true, required: false
    input "relaySwitchs", "capability.relaySwitch", title: "relaySwitch", multiple: true, required: false
    input "sensors", "capability.sensor", title: "sensor", multiple: true, required: false
    input "shockSensors", "capability.shockSensor", title: "shockSensor", multiple: true, required: false
    input "signalStrengths", "capability.signalStrength", title: "signalStrength", multiple: true, required: false
    input "sleepSensors", "capability.sleepSensor", title: "sleepSensor", multiple: true, required: false
    input "smokeDetectors", "capability.smokeDetector", title: "smokeDetector", multiple: true, required: false
    input "soundPressureLevels", "capability.soundPressureLevel", title: "soundPressureLevel", multiple: true, required: false
    input "soundSensors", "capability.soundSensor", title: "soundSensor", multiple: true, required: false
    input "speechRecognitions", "capability.speechRecognition", title: "speechRecognition", multiple: true, required: false
    input "speechSynthesiss", "capability.speechSynthesis", title: "speechSynthesis", multiple: true, required: false
    input "stepSensors", "capability.stepSensor", title: "stepSensor", multiple: true, required: false
    input "switchs", "capability.switch", title: "switch", multiple: true, required: false
    input "switchLevels", "capability.switchLevel", title: "switchLevel", multiple: true, required: false
    input "tamperAlerts", "capability.tamperAlert", title: "tamperAlert", multiple: true, required: false
    input "temperatureMeasurements", "capability.temperatureMeasurement", title: "temperatureMeasurement", multiple: true, required: false
    input "thermostats", "capability.thermostat", title: "thermostat", multiple: true, required: false
    input "thermostatCoolingSetpoints", "capability.thermostatCoolingSetpoint", title: "thermostatCoolingSetpoint", multiple: true, required: false
    input "thermostatFanModes", "capability.thermostatFanMode", title: "thermostatFanMode", multiple: true, required: false
    input "thermostatHeatingSetpoints", "capability.thermostatHeatingSetpoint", title: "thermostatHeatingSetpoint", multiple: true, required: false
    input "thermostatModes", "capability.thermostatMode", title: "thermostatMode", multiple: true, required: false
    input "thermostatOperatingStates", "capability.thermostatOperatingState", title: "thermostatOperatingState", multiple: true, required: false
    input "thermostatSetpoints", "capability.thermostatSetpoint", title: "thermostatSetpoint", multiple: true, required: false
    input "threeAxiss", "capability.threeAxis", title: "threeAxis", multiple: true, required: false
    input "timedSessions", "capability.timedSession", title: "timedSession", multiple: true, required: false
    input "tones", "capability.tone", title: "tone", multiple: true, required: false
    input "touchSensors", "capability.touchSensor", title: "touchSensor", multiple: true, required: false
    input "ultravioletIndexs", "capability.ultravioletIndex", title: "ultravioletIndex", multiple: true, required: false
    input "valves", "capability.valve", title: "valve", multiple: true, required: false
    input "voltageMeasurements", "capability.voltageMeasurement", title: "voltageMeasurement", multiple: true, required: false
    input "waterSensors", "capability.waterSensor", title: "waterSensor", multiple: true, required: false
    input "windowShades", "capability.windowShade", title: "windowShade", multiple: true, required: false
  }
}

mappings {
  path("/dump") {
   action: [
    GET: "dumpAllStuff"
   ]
  }
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

def dumpAllStuff() {
	def resp = []
    resp << dumpValues('accelerationSensor',accelerationSensors)
    resp << dumpValues('actuator',actuators)
    resp << dumpValues('alarm',alarms)
    resp << dumpValues('audioNotification',audioNotifications)
    resp << dumpValues('battery',batterys)
    resp << dumpValues('beacon',beacons)
    resp << dumpValues('bridge',bridges)
    resp << dumpValues('bulb',bulbs)
    resp << dumpValues('button',buttons)
    resp << dumpValues('carbonDioxideMeasurement',carbonDioxideMeasurements)
    resp << dumpValues('carbonMonoxideDetector',carbonMonoxideDetectors)
    resp << dumpValues('colorControl',colorControls)
    resp << dumpValues('colorTemperature',colorTemperatures)
    resp << dumpValues('configuration',configurations)
    resp << dumpValues('consumable',consumables)
    resp << dumpValues('contactSensor',contactSensors)
    resp << dumpValues('doorControl',doorControls)
    resp << dumpValues('energyMeter',energyMeters)
    resp << dumpValues('estimatedTimeOfArrival',estimatedTimeOfArrivals)
    resp << dumpValues('garageDoorControl',garageDoorControls)
    resp << dumpValues('holdableButton',holdableButtons)
    resp << dumpValues('illuminanceMeasurement',illuminanceMeasurements)
    resp << dumpValues('imageCapture',imageCaptures)
    resp << dumpValues('indicator',indicators)
    resp << dumpValues('infraredLevel',infraredLevels)
    resp << dumpValues('light',lights)
    resp << dumpValues('lock',locks)
    resp << dumpValues('lockOnly',lockOnlys)
    resp << dumpValues('mediaController',mediaControllers)
    resp << dumpValues('momentary',momentarys)
    resp << dumpValues('motionSensor',motionSensors)
    resp << dumpValues('musicPlayer',musicPlayers)
    resp << dumpValues('notification',notifications)
    resp << dumpValues('outlet',outlets)
    resp << dumpValues('phMeasurement',phMeasurements)
    resp << dumpValues('polling',pollings)
    resp << dumpValues('powerMeter',powerMeters)
    resp << dumpValues('powerSource',powerSources)
    resp << dumpValues('presenceSensor',presenceSensors)
    resp << dumpValues('refresh',refreshs)
    resp << dumpValues('relativeHumidityMeasurement',relativeHumidityMeasurements)
    resp << dumpValues('relaySwitch',relaySwitchs)
    resp << dumpValues('sensor',sensors)
    resp << dumpValues('shockSensor',shockSensors)
    resp << dumpValues('signalStrength',signalStrengths)
    resp << dumpValues('sleepSensor',sleepSensors)
    resp << dumpValues('smokeDetector',smokeDetectors)
    resp << dumpValues('soundPressureLevel',soundPressureLevels)
    resp << dumpValues('soundSensor',soundSensors)
    resp << dumpValues('speechRecognition',speechRecognitions)
    resp << dumpValues('speechSynthesis',speechSynthesiss)
    resp << dumpValues('stepSensor',stepSensors)
    resp << dumpValues('switch',switchs)
    resp << dumpValues('switchLevel',switchLevels)
    resp << dumpValues('tamperAlert',tamperAlerts)
    resp << dumpValues('temperatureMeasurement',temperatureMeasurements)
    resp << dumpValues('thermostat',thermostats)
    resp << dumpValues('thermostatCoolingSetpoint',thermostatCoolingSetpoints)
    resp << dumpValues('thermostatFanMode',thermostatFanModes)
    resp << dumpValues('thermostatHeatingSetpoint',thermostatHeatingSetpoints)
    resp << dumpValues('thermostatMode',thermostatModes)
    resp << dumpValues('thermostatOperatingState',thermostatOperatingStates)
    resp << dumpValues('thermostatSetpoint',thermostatSetpoints)
    resp << dumpValues('threeAxis',threeAxiss)
    resp << dumpValues('timedSession',timedSessions)
    resp << dumpValues('tone',tones)
    resp << dumpValues('touchSensor',touchSensors)
    resp << dumpValues('ultravioletIndex',ultravioletIndexs)
    resp << dumpValues('valve',valves)
    resp << dumpValues('voltageMeasurement',voltageMeasurements)
    resp << dumpValues('waterSensor',waterSensors)
    resp << dumpValues('windowShade',windowShades)
}

def dumpValues(cap, devices) {
     def resp = [:]
     def entries = []

	if (devices == null) {
    	resp[cap] = entries
    	return resp
     }
    	

		if (devices.hasProperty('id')) {
        	devices = [ devices ]
        }

        def attrs = getAttrsForCap(cap)
        if (attrs.size() < 1) {
        	resp[cap] = entries
    		return resp
     
         }
        devices.each { device ->
        	log.debug "device attributes ${device}"
            
            def value = [:]
            try {
                value['id'] = device.id
                value['name'] = device.displayName
                attrs.each { attr ->
                  value[attr] = device.currentValue(attr)
                }
            } catch (Exception e) {
                log.debug(e)
             }
            entries << value
         }
     
     resp[cap] = entries
    return resp
     
}

def listAllSensors() {
  def resp = []

  sensors.each { sensor ->
    def caps = []
    def cap_class = []
    sensor.capabilities.each { cap ->
      caps << cap.name
      cap_class << cap.getClass().getCanonicalName()
    }
    resp << [id: sensor.id, 
             name: sensor.displayName,
             caps: caps,
             classes: cap_classes
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

def getAttrsForCap(name) {
    def caps = [:] 
    caps['accelerationSensor'] = ['acceleration']
    caps['actuator'] = []
    caps['alarm'] = ['alarm']
    caps['audioNotification'] = []
    caps['battery'] = ['battery']
    caps['beacon'] = ['presence']
    caps['bridge'] = []
    caps['bulb'] = ['switch']
    caps['button'] = ['button','numberOfButtons',]
    caps['carbonDioxideMeasurement'] = ['carbonDioxide']
    caps['carbonMonoxideDetector'] = ['carbonMonoxide']
    caps['colorControl'] = ['color','hue','saturation',]
    caps['colorTemperature'] = ['colorTemperature']
    caps['configuration'] = []
    caps['consumable'] = ['consumableStatus']
    caps['contactSensor'] = ['contact']
    caps['doorControl'] = ['door']
    caps['energyMeter'] = ['energy']
    caps['estimatedTimeOfArrival'] = ['eta']
    caps['garageDoorControl'] = ['door']
    caps['holdableButton'] = ['button','numberOfButtons',]
    caps['illuminanceMeasurement'] = ['illuminance']
    caps['imageCapture'] = ['image']
    caps['indicator'] = ['indicatorStatus']
    caps['infraredLevel'] = ['infraredLevel']
    caps['light'] = ['switch']
    caps['lock'] = ['lock']
    caps['lockOnly'] = ['lock']
    caps['mediaController'] = ['activities','currentActivity',]
    caps['momentary'] = []
    caps['motionSensor'] = ['motion']
    caps['musicPlayer'] = ['level','mute','status','trackData','trackDescription',]
    caps['notification'] = []
    caps['outlet'] = ['switch']
    caps['phMeasurement'] = ['pH']
    caps['polling'] = []
    caps['powerMeter'] = ['power']
    caps['powerSource'] = ['powerSource']
    caps['presenceSensor'] = ['presence']
    caps['refresh'] = []
    caps['relativeHumidityMeasurement'] = ['humidity']
    caps['relaySwitch'] = ['switch']
    caps['sensor'] = []
    caps['shockSensor'] = ['shock']
    caps['signalStrength'] = ['lqi','rssi',]
    caps['sleepSensor'] = ['sleeping']
    caps['smokeDetector'] = ['smoke','carbonMonoxide',]
    caps['soundPressureLevel'] = ['soundPressureLevel']
    caps['soundSensor'] = ['sound']
    caps['speechRecognition'] = ['phraseSpoken']
    caps['speechSynthesis'] = []
    caps['stepSensor'] = ['goal','steps',]
    caps['switch'] = ['switch']
    caps['switchLevel'] = ['level']
    caps['tamperAlert'] = ['tamper']
    caps['temperatureMeasurement'] = ['temperature']
    caps['thermostat'] = ['coolingSetpoint','coolingSetpointMin','coolingSetpointMax','heatingSetpoint','heatingSetpointMin','heatingSetpointMax','schedule','temperature','thermostatFanMode','thermostatMode','thermostatOperatingState','thermostatSetpoint','thermostatSetpointMin','thermostatSetpointMax',]
    caps['thermostatCoolingSetpoint'] = ['coolingSetpoint','coolingSetpointMin','coolingSetpointMax',]
    caps['thermostatFanMode'] = ['thermostatFanMode']
    caps['thermostatHeatingSetpoint'] = ['heatingSetpoint','heatingSetpointMin','heatingSetpointMax',]
    caps['thermostatMode'] = ['thermostatMode']
    caps['thermostatOperatingState'] = ['thermostatOperatingState']
    caps['thermostatSetpoint'] = ['thermostatSetpoint','thermostatSetpointMin','thermostatSetpointMax',]
    caps['threeAxis'] = ['threeAxis']
    caps['timedSession'] = ['sessionStatus','timeRemaining',]
    caps['tone'] = []
    caps['touchSensor'] = ['touch']
    caps['ultravioletIndex'] = ['ultravioletIndex']
    caps['valve'] = ['contact','valve',]
    caps['voltageMeasurement'] = ['voltage']
    caps['waterSensor'] = ['water']
    caps['windowShade'] = ['windowShade']
	return caps[name]

}