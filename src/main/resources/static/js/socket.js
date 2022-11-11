import Peer from "simple-peer";

let conn = new WebSocket('ws://localhost:8080/socket');

function send(message){
    conn.send(JSON.stringify(message));
}

configuration = null;
let peerConnection = new RTCPeerConnection(configuration);

