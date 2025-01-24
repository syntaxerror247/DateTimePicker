package com.mishragames.godot.plugin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import org.godotengine.godot.Dictionary
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot
import java.util.Calendar

class DateTimePicker(godot: Godot): GodotPlugin(godot) {

	override fun getPluginName() = "DateTimePicker"

	override fun getPluginSignals(): MutableSet<SignalInfo> {
		val signals: MutableSet<SignalInfo> = mutableSetOf()
		signals.add(SignalInfo("onDatePicked", Dictionary::class.java))
		signals.add(SignalInfo("onTimePicked", Dictionary::class.java))
		signals.add(SignalInfo("onDateTimePicked", Dictionary::class.java))
		return signals
	}

	@UsedByGodot
	fun pickDate() {
		runOnUiThread {
			val calendar = Calendar.getInstance()
			val year = calendar.get(Calendar.YEAR)
			val month = calendar.get(Calendar.MONTH)
			val day = calendar.get(Calendar.DAY_OF_MONTH)

			val activity = godot.getActivity()
			activity?.let {
				val datePickerDialog = DatePickerDialog(it, android.R.style.Theme_DeviceDefault_Dialog,
					{ _, selectedYear, selectedMonth, selectedDay ->
					val dateDict = Dictionary()
					dateDict["day"] = selectedDay
					dateDict["month"] = selectedMonth + 1 // Adjust for 0-based month
					dateDict["year"] = selectedYear
					emitSignal("onDatePicked", dateDict)
				}, year, month, day)
				datePickerDialog.show()
			}
		}
	}

	@UsedByGodot
	fun pickTime(is24HourView: Boolean) {
		runOnUiThread {
			val calendar = Calendar.getInstance()
			val hour = calendar.get(Calendar.HOUR_OF_DAY)
			val minute = calendar.get(Calendar.MINUTE)

			val activity = godot.getActivity()
			activity?.let {
				val timePickerDialog = TimePickerDialog(it, android.R.style.Theme_DeviceDefault_Dialog,
					{ _, selectedHour, selectedMinute ->
					val timeDict = Dictionary()
					timeDict["hour"] = selectedHour
					timeDict["minute"] = selectedMinute
					emitSignal("onTimePicked", timeDict)
				}, hour, minute, is24HourView)
				timePickerDialog.show()
			}
		}
	}

	@UsedByGodot
	fun pickDateTime(is24HourView: Boolean) {
		runOnUiThread {
			val calendar = Calendar.getInstance()
			val year = calendar.get(Calendar.YEAR)
			val month = calendar.get(Calendar.MONTH)
			val day = calendar.get(Calendar.DAY_OF_MONTH)
			val hour = calendar.get(Calendar.HOUR_OF_DAY)
			val minute = calendar.get(Calendar.MINUTE)

			val activity = godot.getActivity()
			activity?.let {
				// First, show the date picker dialog
				val datePickerDialog = DatePickerDialog(it, android.R.style.Theme_DeviceDefault_Dialog,
					{ _, selectedYear, selectedMonth, selectedDay ->
					// Once the date is picked, show the time picker dialog
					val timePickerDialog = TimePickerDialog(it, android.R.style.Theme_DeviceDefault_Dialog,
						{ _, selectedHour, selectedMinute ->
						val dateTimeDict = Dictionary()
						dateTimeDict["day"] = selectedDay
						dateTimeDict["month"] = selectedMonth + 1
						dateTimeDict["year"] = selectedYear
						dateTimeDict["hour"] = selectedHour
						dateTimeDict["minute"] = selectedMinute
						emitSignal("onDateTimePicked", dateTimeDict)
					}, hour, minute, is24HourView)

					timePickerDialog.show()
				}, year, month, day)

				datePickerDialog.show()
			}
		}
	}
}
