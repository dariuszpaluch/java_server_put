"use strict";

var API = "http://localhost:9997/";

var prepareModel = function (url, idColumnName) {
  var self = ko.observableArray();

  self.url = url;
  self.idColumnName = idColumnName || "id";

  self.get = function () {
    $.ajax({
      type: 'GET',
      url: API + self.url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      success: function (data) {
        if(self.sub) {
          self.sub.dispose();
        }
        self.removeAll();
        data.forEach(function (item) {
          const object = ko.mapping.fromJS(item, { ignore: ["link"] });

          object.links = [];
          if($.isArray(item.link)) {
            item.link.forEach(function(link) {
              object.links[link.params.rel] = link.href;
            });
          } else {
            object.links[item.link.params.rel] = item.link.href;
          }



          self.push(object);

          console.log("DAREK");
          ko.computed(function() {
            return ko.toJSON(object);
          }).subscribe(function() {
            self.updateRequest(object);
          });

        });

        self.sub = self.subscribe(function(changes) {
          console.log(changes);
          console.log("TUTAJ");
          changes.forEach(function(change) {
            switch(change.status) {
              case "deleted": {
                console.log("DELETED");
                self.deleteRequest(change.value);
                break;
              }
              case "added": {
                console.log("ADDED");
                self.saveRequest(change.value);
                break;
              }
            }
          });
        }, null, "arrayChange");



      },
      error: function (error) {
        console.error(error);
      }
    });
  };

  self.updateRequest = function(object) {
    $.ajax({
      url: object.links['self'],
      dataType: "json",
      contentType: "application/json",
      data: ko.mapping.toJSON(object, { ignore: ["links"] }),
      method: "PUT"
    });
  }

  self.saveRequest = function(object) {
    console.log('save', object);
    console.log(self.url);

    $.ajax({
      url: API + self.url,
      contentType: 'application/json; charset=UTF-8',
      dataType: "json",
      data: ko.mapping.toJSON(object),
      method: "POST",
      success: function(data) {
        object[self.idColumnName](data[self.idColumnName]); //set id from api

        if(data.course) {
          object.course.name(data.course.name);
        }
      },
      error: function(error) {
        console.error(error);
      }
    });
  };

  self.add = function(form) {
    var data = {};

    $(form).serializeArray().map(function (property) {
      if(property.name !== 'index') {
        data[property.name] = property.value;
      }
    });

    //clear only edit inputs
    $(form).find("tbody").filter(function () {
      return $(this).hasClass('edit');
    }).find("input").each(function () {
      $(this).val('');
    });

    data[self.idColumnName] = null;

    const observableData = ko.mapping.fromJS(data);

    self.push(observableData);
  }
  self.deleteRequest = function(object) {
    $.ajax({
      url: object.links.self,
      method: "DELETE"
    });
  };

  self.delete = function() {

    self.remove(this);
  };


  return self;
};

function studentManagerViewModel() {
  var self = this;
  self.students = prepareModel("students", "index");
  self.students.showGrades = function() {
    var index = this.index();
    var firstName = this.firstName();
    var lastName = this.lastName();
    self.grades.studentIndex = index;
    self.grades.firstName(firstName);
    self.grades.lastName(   lastName);
    self.grades.index(index);
    self.grades.url = "students/" + index + "/grades";
    self.grades.get();

    window.location = "#grades-table";
  };
  self.students.get();

  self.courses = prepareModel("courses");
  self.courses.get();

  self.grades = prepareModel();

    self.grades.firstName = ko.observable('');
    self.grades.lastName = ko.observable('');
    self.grades.index = ko.observable(1);

  self.grades.add = function(form) {
    var data = {};
    $(form).serializeArray().map(function(property) {

      if(property.name === 'course') {
        data.course = {
          id: property.value,
          name: null
        }
      } else {
        data[property.name] = property.value;
      }
    });

    data['id'] = null;

    $(form).find("tbody").filter(function () {
      return $(this).hasClass('edit');
    }).find("input").each(function () {
      $(this).val('');
    });

    data['id'] = null;

    const observableData = ko.mapping.fromJS(data);
    self.grades.push(observableData);
  };
}


$(document).ready(function () {
  ko.applyBindings(new studentManagerViewModel());
});
