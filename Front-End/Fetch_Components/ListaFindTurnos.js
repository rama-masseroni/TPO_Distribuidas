import React, { useCallback, useEffect, useState } from 'react';
import { Image, ActivityIndicator, FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

export default function ListaFindTurnos({screenName}){
    const [isLoading, setLoading] = useState(true);
    const [data, setData] = useState([]);
    const navigation= useNavigation();
    screenName = 'Principal'

    function onConfirm(){
        navigation.navigate(screenName);
    }

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
            <View style={{ flex: 1, flexDirection: 'row', marginStart: 40 }}>
                <TouchableOpacity activeOpacity={.7} onPress={() => {onConfirm()}}>
                    <Image style={{ height: 60, width: 60 }} source={require('../Images/add_turno.png')} />
                </TouchableOpacity>
            </View>
        </View>
    )
    const styles = StyleSheet.create({
        turno: {
            flexDirection: 'row',
            width: 350,
            paddingStart: 17,
            paddingTop: 10,
            paddingBottom: 10,
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
