extends Control

var _plugin_name = "DateTimePicker"
var _android_plugin

var is24HourView = true

func _ready():
	if Engine.has_singleton(_plugin_name):
		_android_plugin = Engine.get_singleton(_plugin_name)
		_android_plugin.connect("onDateTimePicked",date_time_picked)
		_android_plugin.connect("onDatePicked",date_time_picked)
		_android_plugin.connect("onTimePicked",date_time_picked)
	else:
		printerr("Couldn't find plugin " + _plugin_name)

func date_time_picked(dict: Dictionary) -> void:
	$VBoxContainer/console.text += str(dict) + "\n"

func _on_datetime_picker_pressed() -> void:
	if _android_plugin:
		_android_plugin.pickDateTime(is24HourView)


func _on_date_picker_pressed() -> void:
	if _android_plugin:
		_android_plugin.pickDate()


func _on_time_picker_pressed() -> void:
	if _android_plugin:
		_android_plugin.pickTime(is24HourView)
