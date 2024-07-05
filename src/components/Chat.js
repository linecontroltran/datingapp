import React, { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const Chat = () => {
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState('');
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws');
    const client = new Client({
      webSocketFactory: () => socket,
      onConnect: () => {
        client.subscribe('/topic/public', (messageOutput) => {
          const message = JSON.parse(messageOutput.body);
          setMessages((prevMessages) => [...prevMessages, message]);
        });
      },
    });

    client.activate();
    setStompClient(client);

    return () => {
      if (stompClient !== null) {
        stompClient.deactivate();
      }
    };
  }, [stompClient]);

  const sendMessage = () => {
    if (stompClient && message.trim() !== '') {
      const chatMessage = {
        sender: 'User', // Change this to the current user's name
        content: message,
        type: 'CHAT',
      };
      stompClient.publish({ destination: '/app/chat.sendMessage', body: JSON.stringify(chatMessage) });
      setMessage('');
    }
  };

  return (
    <div>
      <div>
        {messages.map((msg, index) => (
          <div key={index}>{msg.sender}: {msg.content}</div>
        ))}
      </div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Type a message..."
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
};

export default Chat;
