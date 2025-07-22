# Debug device via WI-FI

## Manual steps

### Make sure you are on the same network

### Get the phone IP Address

- Settings -> Wi-Fi -> WI-FI SETTINGS -> Additional settings -> IP Address
- Settings -> About Phone -> Status Information -> IP Address

### Connect the Phone via the USB cable

### Open Terminal and run the following commands

View -> Tool Windows -> Terminal) or Alt + F12

- `adb devices`
- `adb tcpip 5555`
- `adb connect <PHONE_IP_ADDRESS>`
- `adb devices`
- 
### Disconnect the Phone from the USB cable

## Pair Devices Using Wi-FI via QR code

Android 11+