var express = require('express');
var app = express();

app.get('/search', function (req, res) {
    var value = req.param("value");
    console.log("search for " + value);
    if ("Bill" ==  value) {
        res.send({firstname:'Bill', name:'Burke', id: 0});
    } else {
        req.status(404);
    }
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
