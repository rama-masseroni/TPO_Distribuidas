import React, { useCallback, useEffect, useState } from 'react';
import { Image, ActivityIndicator, FlatList, StyleSheet, Text, TouchableOpacity, View, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';

export default function ListaFindTurnos(props) {
    const [isLoading, setLoading] = useState(true);
    const [data, setList] = useState([]);
    const navigation = useNavigation();
    // screenName = 'Principal'

    const [reply, setReply] = useState('');

    function onConfirm(id, item) {
        console.log(id);
        console.log(item.medico.id);
        console.log(item.especialidad);
        console.log(item.fecha);
        console.log(item.hora);

        fetch('http://192.168.0.160:1234/tpo/reservarTurno', {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'idP=' + id + '&idM=' + item.medico.id + '&esp=' + item.especialidad + '&fecha=' + item.fecha + '&hora=' + item.hora,
            // {
            //     idP: id,
            //     idM: item.medico.id,
            //     esp: item.especialidad,
            //     fecha: item.fecha,
            //     hora: item.hora,
            // } 
        }).then((response) => {
            response.json();
        }).then(data => {
            console.log(data);
            navigation.push('Principal');
        }).catch((error) => console.error(error));

        // navigation.navigate('Principal');
    }

    useEffect(() => {
        // console.log(props.dia);
        // console.log(props.espe);
        // console.log(props.medico);
        // fetch('https://jsonplaceholder.typicode.com/users')
        // .then((response) => response.json())
        // .then((json) => setData(json))
        // .catch((error) => console.error(error))
        // .finally(() => setLoading(false));

        fetch('http://192.168.0.160:1234/tpo/buscarTurnos', {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'dia=' + props.dia + '&especialidad=' + props.espe,
        })
            .then((response) => response.json())
            .then(data => {
                setList(data);
                // setSID(data[0].idUsrMed);
            })
            .catch((error) => console.error(error))
            .finally(() => { setLoading(false) });
    }, []);

    despliegue = ({ item }) => (
        <View style={[styles.turno, { alignItems: "center" }]}>
            <View style={{ flex: 2 }}>
                <Text style={{ fontWeight: 'bold', fontSize: 20 }}>
                    {item.medico.apellido},</Text>
                <Text style={{ fontWeight: 'bold', fontSize: 20 }}>
                    {item.medico.nombre}
                </Text>

                <View style={{ marginTop: 5, marginBottom: 8, flexDirection: 'row' }}>
                    <Text>{item.fecha}</Text>
                    <Text style={{ marginStart: 17 }}>{item.hora}</Text>
                </View>
            </View>
            <TouchableOpacity style={{ marginEnd: 40, }} activeOpacity={.7} onPress={() => { onConfirm(props.id, item) }}>
                <Image style={{ height: 60, width: 60 }} source={require('../Images/add_turno.png')} />
            </TouchableOpacity>
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
                    keyExtractor={(item, index) => index.toString()}
                    ListFooterComponent={function () {
                        return (<Image style={{ alignSelf: 'center', marginTop: 21, marginBottom: 21, }} source={require('../Images/footer.png')} />);
                    }}
                />
            )}
        </View>
    );
}
