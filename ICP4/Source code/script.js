function getGithubInfo(user) {
    // to send the GET request using the instance of XMLhttpRequest
    var username='https://api.github.com/users/'+user;
    console.log(username);
    $.ajax({
        type: "GET",
        url: username,
        dataType: 'json',

    }).done(function(data){
        showUser(data);

    }).fail(function(){
        console.log("Some error Happened");
        noSuchUser(user);
    });

}

function showUser(user) {
    document.getElementById('imgavg').src=user.avatar_url;
    document.getElementById('txtname').innerText=user.name;
    document.getElementById('txtid').innerText=user.id;
    document.getElementById('txturl').href=user.url;
    document.getElementById('txturl').innerText=user.html_url;
    document.getElementById('txtrepository').innerText=user.public_repos;
}
function noSuchUser(username) {
    //to display the correct content
    if(data.message == "Not Found" || username == '') {
        alert("User not found");
    }
}
$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //To check whether user press the enter or not
        if (e.which == 13) {
            //retrives user entered
            username = $(this).val();
            //to reset the text in text field
            $(this).val("");
            //to store the users response
            getGithubInfo(username);
            //sucessfull results response are shown

        }
    })
});