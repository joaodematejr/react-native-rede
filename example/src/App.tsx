import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';
import { handleRede } from 'react-native-smart-rede';

export default function App() {
  function handleAlert() {
    handleRede('CREDITO_PARCELADO_EMISSOR', 500, 5)
      .then((a) => {
        console.log('linha => 16', a);
      })
      .catch((e) => {
        console.log('linha => 19', e);
      });
  }

  return (
    <View style={styles.container}>
      <Button
        onPress={() => handleAlert()}
        title="Learn More"
        color="#841584"
        accessibilityLabel="Learn more about this purple button"
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 10,
    alignItems: 'stretch',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
