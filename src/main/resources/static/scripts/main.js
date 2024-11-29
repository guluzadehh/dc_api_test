$(document).ready(function () {
    fetchStudents();

    $("#student-form").submit(function (e) { 
        e.preventDefault();
        
        const data =  JSON.stringify({
            first_name: $("#first_name").val(),
            last_name: $("#last_name").val(),
            surname: $("#surname").val(),
            birthdate: $("#birthdate").val(),
            group: $("#group").val(),
        });

        console.log(data)

        $.ajax({
            type: "POST",
            url: "/api/students",
            data: data,
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                fetchStudents();
            },
            error: function (xhr, status, error) {
                const res = xhr.responseJSON;
                const errorsDiv = $(".student-creator__errors");
                errorsDiv.empty();

                for (let error of res.errors) {
                    errorsDiv.append(`<div>${error}</div>`);
                }
            }
        });
    });

    $("#student-list-refresh").on("click", fetchStudents);

    $(".student-list").on("click", "#student-item-delete", function () { 
        const studentId = $(this).data("student-id");

        $.ajax({
            type: "DELETE",
            url: `/api/students/${studentId}`,
            success: fetchStudents
        });
    });

    function fetchStudents() {
        $.ajax({
            type: "GET",
            url: "/api/students",
            success: function (res) {
                let div = $(".student-list__div");
                div.empty();
    
                for (let student of res) {
                    div.append(
                        `<div class="student-list__item">
                            <div>
                                ${student.group}: ${student.fname} ${student.lname}, ${student.surname}
                                <br />
                                ${student.birthdate}
                            </div>
                            <button type="button" id="student-item-delete" data-student-id="${student.id}">Удалить</button>
                        </div>`
                    );
                }
            }
        });
    }
});
