import React from 'react';
import { TouchableOpacity, StyleSheet, Text, View, TextInput, KeyboardAvoidingView } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createDrawerNavigator } from '@react-navigation/drawer';
import { LinearGradient } from 'expo-linear-gradient';
import Paciente from './Paciente'

const Stack = createStackNavigator();

function Registro({ navigation }) {
  return (

    <LinearGradient
      colors={['#FF3535', 'white']}
      start={[0, 0.2]}
      end={[0, 0.4]}
      style={styles.container}
    >
      <KeyboardAvoidingView behavior={'position'}>

        <Text style={styles.titulo}>CRUCIS</Text>
        <TextInput
          placeholder={"E-Mail"}
          style={[styles.inputBox, { marginTop: 70 }]}
          textContentType={"emailAddress"}
          keyboardType={"email-address"}
        />
        <TextInput
          placeholder={"Contraseña"}
          style={styles.inputBox}
          textContentType={"password"}
          secureTextEntry={true}
        />
      </KeyboardAvoidingView>
      <TouchableOpacity activeOpacity={.7} style={{ marginTop: 70 }} onPress={() => {
        navigation.navigate('Paciente', { screen: 'Principal' });
      }}>
        <View style={styles.primaryButton} >
          <Text style={{ color: '#FFFF', fontSize: 20, fontWeight: 'bold', borderColor: '#ff3434' }}>Iniciar Sesión</Text>
        </View>
      </TouchableOpacity>
    </LinearGradient>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    alignContent:'center',
    justifyContent: 'flex-start',
  },
  titulo: {
    fontSize: 85,
    color: '#e60000',
    borderColor: '#ffbfbf',
    alignSelf: 'center',
    marginTop: 190,
    shadowColor: 'gray',
    shadowOffset: { width: 3, height: 1 },
  },
  inputBox: {
    height: 64,
    width: 300,
    paddingStart: 24,
    marginBottom: 24,
    alignSelf: 'center',
    borderRadius: 7,
    borderColor: 'black',
    backgroundColor: '#F8F8F8',
    borderWidth: 1,
  },
  primaryButton: {
    width: 190,
    height: 60,
    alignItems: 'center',
    justifyContent: 'center',

    borderRadius: 10,
    backgroundColor: '#FF3434',
  },
  header: {
    marginTop: 121,
    color: 'red',
    fontSize: 35,
    fontWeight: '700'
  },
  subHeader: {
    color: '#FF3535',
    fontSize: 22,
    fontWeight: '600'
  },
  dataBox: {
    width: 350,
    paddingStart: 11,
    paddingTop: 10,
    paddingBottom: 6,
    paddingEnd: 10,
    borderColor: 'black',
    borderRadius: 1,
    borderWidth: 1,
    backgroundColor: '#E9E9E9'
  },
  turnosCont: {
    height: 205,
    width: 350,
    borderTopColor: 'black',
    borderTopWidth: 2,
    borderBottomColor: 'black',
    borderBottomWidth: 2,
  },
});


export default function App() {

  return (
    <NavigationContainer>
      <Stack.Navigator headerMode={'none'}>
        <Stack.Screen
          name="Registro"
          component={Registro}
        />
        <Stack.Screen
          name="Paciente"
          component={Paciente}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
