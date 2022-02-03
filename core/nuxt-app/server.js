const express = require('express');
const server = express();

server.get('/', (req, res) => {
    res.send('Successful');
});

server.listen(3000);
