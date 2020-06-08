import React, { useCallback, useEffect, useState } from 'react';
import { Image, ActivityIndicator, FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

export default FetchApp = () => {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);

  const onConfirm = useCallback(
    item => { alert('Has confirmado el turno con el especialista:\n' + item.name); }
  );

  const onCancel = useCallback(
    item => { alert('Se ha cancelado el turno con el especialista:\n' + item.name); }
  );
  useEffect(() => {
    fetch('https://jsonplaceholder.typicode.com/users')
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => console.error(error))
      .finally(() => setLoading(false));
  }, []);

  despliegue = ({ item }) => (
    <View style={styles.turno}>
      <View style={{ flex: 2 }}>
        <View style={{ flexDirection: 'row', alignContent: 'space-around' }}>
          <Text style={{ fontSize: 20 }}>
            ID:
          </Text>
          <Text style={{ fontWeight: 'bold', marginLeft: 5, fontSize: 20 }}>
            {item.id}
          </Text>
        </View>
        <Text style={{ fontWeight: 'bold', fontSize: 20 }}>
          {item.name}
        </Text>

        <View style={{ marginTop: 5, marginBottom: 8, flexDirection: 'row' }}>
          <Text>DD</Text>
          <Text> de </Text>
          <Text>MMMM</Text>
          <Text style={{ marginStart: 17 }}>HH:MM</Text>
        </View>
      </View>
      <View style={{ flex: 1, flexDirection: 'row', marginTop: 30 }}>
        <TouchableOpacity activeOpacity={.7} onPress={() => onConfirm(item)}>
          <Image style={{ height: 30, width: 30 }} source={require('../Images/green_check.png')} />
        </TouchableOpacity>
        <TouchableOpacity style={{ marginStart: 20 }} activeOpacity={.7} onPress={() => onCancel(item)}>
          <Image style={{ height: 30, width: 30 }} source={require('../Images/red_cross.png')} />
        </TouchableOpacity>
      </View>
    </View>
  )
  const styles = StyleSheet.create({
    turno: {
      flexDirection: 'row',
      width: 350,
      paddingStart: 17,
      paddingTop: 7,
      backgroundColor: '#E8E8E8',
      borderBottomColor: '#9f9f9f',
      borderBottomWidth: 1,
    },
  });

  return (
    <View>
      {isLoading ? <ActivityIndicator style={{ marginTop: 10 }} /> : (
        <FlatList
          data={data}
          renderItem={despliegue}
          keyExtractor={ (item, index) => index.toString() }  
          ListFooterComponent={function () {
            return (<Image style={{ alignSelf: 'center', marginTop: 21, marginBottom: 21, }} source={require('../Images/footer.png')} />);
          }}
        />
      )}
    </View>
  );
}


