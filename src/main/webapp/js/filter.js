var fields = ["Name", "X", "Y", "Date", "Area", "Population", "MetersAboveSeaLevel", "Timezone", "Government", "StandardOfLiving", "Height", "Birthday"]

fields.forEach((element) => {
    $('.is' + element + 'Disabled').change(function () {
        if ($('.is' + element + 'Disabled').is(':checked')) {
            $('.' + element).removeAttr("disabled");
        } else {
            $('.' + element).attr("disabled", "true");
        }
    });
})

var form = document.forms.namedItem("filterForm");
form.addEventListener('submit', function (ev) {

    var formData = new FormData(form);
    var request = new XMLHttpRequest();
    request.responseType = 'document';
    var getStr = "?";
    for (var pair of formData.entries()) {
        getStr += pair[0] + '=' + pair[1] + '&';
    }
    getStr = getStr.substr(0, getStr.length - 1);
    request.open("GET", "/lab1/filter" + getStr);

    request.onload = function (oEvent) {
        if (request.status === 200) {
            var filteredCities = [];
            var rawData = request.response.getElementsByTagName("cities")[0];
            var k, i, j, oneRecord, oneObject, innerObject;
            for (i = 0; i < rawData.children.length; i++) {
                oneRecord = rawData.children[i];
                oneObject = filteredCities[filteredCities.length] = {};
                for (j = 0; j < oneRecord.children.length; j++) {
                    if (oneRecord.children[j].children.length !== 0) {
                        innerObject = oneObject[oneRecord.children[j].tagName] = {};
                        for (k = 0; k < oneRecord.children[j].children.length; k++) {
                            innerObject[oneRecord.children[j].children[k].tagName] = oneRecord.children[j].children[k].textContent;
                        }
                        oneObject[oneRecord.children[j].tagName] = innerObject;
                    } else {
                        oneObject[oneRecord.children[j].tagName] = oneRecord.children[j].textContent;
                    }
                }
            }
            $('.table-rows').remove();
            var html;
            for (i = 0; i < filteredCities.length; i++) {
                html += "<tr class='table-rows'><td>" + filteredCities[i].id + "</td><td>" + filteredCities[i].name + "</td><td>" + filteredCities[i].coordinates.x
                    + "</td><td>" + filteredCities[i].coordinates.y + "</td><td>" + filteredCities[i].creationDate + "</td><td>" + filteredCities[i].area
                    + "</td><td>" + filteredCities[i].population + "</td><td>" + filteredCities[i].metersAboveSeaLevel
                    + "</td><td>" + filteredCities[i].timezone + "</td><td>" + filteredCities[i].government
                    + "</td><td>" + filteredCities[i].standardOfLiving + "</td><td>" + filteredCities[i].governor.height
                    + "</td><td>" + filteredCities[i].governor.birthday + "</td>" +
                    "<td><a href=\"edit?id=\"" + filteredCities[i].id + "\">Edit</a>" +
                    "  <a href=delete?id=\"" + filteredCities[i].id + "\">Delete</a></td></tr>";
            }
            $('table').append(html);
            console.log(filteredCities);
        } else {
            console.log("Error " + request.status + " occurred when trying to upload your file");
        }
    };

    request.send(formData);
    ev.preventDefault();
}, false);
