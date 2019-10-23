$(function () {
    $('#navHome').on('click', function () {
        window.location.href = "/";
    });
});

$(function () {
    $('#ddlOrder').on('click', function () {
        // alert("Data: dddd");

        window.location.href = "/order";

        // var Status = $(this).val();
        // $.ajax({
        //     url: 'Ajax/StatusUpdate.php',
        //     data: {
        //         text: $("textarea[name=Status]").val(),
        //         Status: Status
        //     },
        //     dataType : 'json'
        // });
    });
});

$(function () {
    $('#navLogout').on('click', function () {
        window.location.href = "/logout";
    });
});