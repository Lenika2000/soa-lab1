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

