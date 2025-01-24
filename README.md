# DateTimePicker Plugin  

**DateTimePicker** is a Godot Android plugin that provides native Android **Date Picker**, **Time Picker**, and **DateTime Picker** 

## Installation
- download latest release from [releases](https://github.com/syntaxerror247/DateTimePicker/releases).
- unzip the release archive.
- copy `addon` folder to your Godot project's root directory.
- enable the plugin via the `Plugins` tab of `Project->Project Settings...` menu, in the Godot Editor.

## Methods
- `pickDate()`: Displays a date picker dialog. Emits the `onDatePicked` signal when a date is selected.
  
- `pickTime(is24HourView: bool)`: Displays a time picker dialog. Emits the `onTimePicked` signal when a time is selected.
    - is24HourView: If `true`, the time picker will display in 24-hour format. If `false`, it will display in 12-hour format.
      
- `pickDateTime(is24HourView)`: Displays both date and time picker dialogs in sequence. Emits the `onDateTimePicked` signal when both are selected.
    - is24HourView: If `true`, the time picker will display in 24-hour format. If `false`, it will display in 12-hour format.

## Signals
- `onDatePicked(Dictionary date)`
  - day: Selected day
  - month: Selected month (1–12)
  - year: Selected year
- `onTimePicked(Dictionary time)` 
  - hour: Selected hour (0–23)  
  - minute: Selected minute (0–59)  
- `onDateTimePicked(Dictionary dateTime)`
  - day: Selected day
  - month: Selected month (1–12)
  - year: Selected year
  - hour: Selected hour (0–23)  
  - minute: Selected minute (0–59)
  
## Screenshots
![1000187047](https://github.com/user-attachments/assets/80120b77-ee07-4a36-993a-bd97931059f4)
![1000187048](https://github.com/user-attachments/assets/44217d6a-cdd1-4cb9-8938-4d1b9799ed21)
