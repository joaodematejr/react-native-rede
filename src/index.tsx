import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-smart-rede' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const SmartRede = NativeModules.SmartRede
  ? NativeModules.SmartRede
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function handleRede(
  type: string,
  value: number,
  installments: number
): Promise<number> {
  return SmartRede.handleRede(type, value, installments);
}
