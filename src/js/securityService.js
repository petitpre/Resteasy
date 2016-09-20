var express = require('express');
var app = express();

app.get('/search', function (req, res) {
    res.send({firstname:'Bill', lastname:'Burke', id: 0});
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
