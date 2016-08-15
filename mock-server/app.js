var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var util = require("util");
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.post('/login', function(req, res) {
  console.log(util.inspect(req, false, null));
  if (req.body.mobile === "123" && req.body.password === "dev") {
    res.json({"auth_token":"aldjflakdsjfklasdjlfkasjd","name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","user_role":"donor/organizer"});
  } else {
    res.status(403).end();
  }
});


app.post("/Doner/Register", function(req, res) {
  if (req.body.firstname=== "Vignesh") {
    res
    .status(200)
    .json({"auth_token":"aldjflakdsjfklasdjlfkasjd","name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","user_role":"donor/organizer"})
    .end();
  } else {
    res.status(400).end();
  }
});

app.post("/user/:mobile_number", function(req, res) {
  if (req.param.mobile_number === "32322232322" && req.body.name === "Vignesh") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
});

app.get("/donation-requests", function (req, res) {
  res.json([{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani1","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 1","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani2","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 2","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani3","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 3","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani4","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 4","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani5","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 5","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani6","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 6","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_1","bloodgroup":"O +ve","pat_name":"Mani7","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 7","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"},{"request_id":"some_unique_id_2","bloodgroup":"O +ve","pat_name":"Mani8","age":30,"date":"2015-07-20","time":"12:23","number_of_units":12.3,"mobileno":"23232322323","location":"Chennai","hospital":"Egmore Govt hospital 8","address":"24, RH Street, Royapettah","city":"Chennai","state":"Tamil nadu","status":"Pending"}]);
})

app.post("/donation-requests", function (req, res) {
  if (req.body.patient_name=== "Mani") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/verify", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/assign", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.organizer_id === "23222366564") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/donate", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.donor_id === "9838383838") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.delete("/donation-requests/:donation_request_id", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.get("/donation-requests/:donation_request_id/responses", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1") {
    res.json([{"name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","last_donated_date":"2015-jul-15"},{"name":"Vignesh","mobile_number":"9838383838","gender":"Male","blood_group":"O +ve","work_location":"CHennai","home_location":"Chennai","last_donated_date":"2015-jul-15"}]);
  } else {
    res.status(400).end();
  }
})

app.post("/donation-requests/:donation_request_id/responses", function (req, res) {
  if (req.params.donation_request_id=== "some_unique_id_1" && req.body.donor_id === "9838383838" && req.body.accept === true) {
    res.status(200).end();
  } else {
    res.status(400).end();
  }
})

app.get("/organizers", function (req, res) {
  res.json([
  {
    "name": "Vignesh",
    "mobile_number": "9838383838",
    "gender": "Male",
    "blood_group": "O +ve",
    "work_location": "CHennai",
    "home_location": "Chennai"
  }
  ]);
})

app.get("/search", function (req,res) {
  console.log(req.query);
  if (req.query.blood_group === "O+ve") {
    res.json ([
  {
    "name": "Vignesh",
    "mobile_number": "9838383838",
    "gender": "Male",
    "blood_group": "O +ve",
    "work_location": "CHennai",
    "home_location": "Chennai"
  }
]);
  } else {
    res.json([]);
  }
})

app.get("/logout", function (req, res) {
  res.status(200).end();
})

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});
// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

module.exports = app;
