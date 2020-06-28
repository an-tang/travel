function summit() {
    var post = {};
    var formData = {};
    var idTour = parseInt(document.getElementById('id').innerHTML);

    formData.name = document.getElementById("myForm").elements[0].value;
    formData.phone = parseInt(document.getElementById("myForm").elements[1].value);
    formData.address = document.getElementById("myForm").elements[2].value;
    formData.passengers = parseInt(document.getElementById("myForm").elements[3].value);
    formData.decription = document.getElementById("myForm").elements[4].value;
    post.order = JSON.stringify(formData);
    post.order = JSON.parse(post.order);
    post.idTour = idTour;

    $.ajax({
        type: "POST",
        url: "manager/rest/order",
        data: post,
        success: function () {
            alert("success");
        },
        dataType: "json",
        contentType: "application/json"
    });
}