

function getGithubInfo(user) {
    //Defining the hard code url od the github to a variable.
    var url  ='https://api.github.com/users/'+user;
    //ajax call to get the request of the url and return type data is in json object.
    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',

    }).done(function(data){
        gitUserInfo(data);

    }).fail(function(){
        console.log("Error occured while getting the data for the user"+user);
        if(data.message == "Not Found" || user == '') {
            alert("User not found. Please enter correct user information");
        }
    });

}

function gitUserInfo(data) {
    document.getElementById('imgavg').src=data.avatar_url;
    document.getElementById('loginName').innerText=data.name;
    document.getElementById('logonID').innerText=data.id;
    document.getElementById('homeUrl').innerText=data.html_url;
    document.getElementById('homeRepository').innerText=data.public_repos;
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        // event to verify the value of key is enter
        if (e.which == 13 || e.keycode == 13) {
            // getting the input value
            username = $(this).val();
            //dafault to text value
            $(this).val("");
            if(username == ''){
                alert("please enter UserName");
            }
          //calling method to pass request
            getGithubInfo(username);
        }
    })


});

function handle(){
        var username = document.getElementById("username").value;
        document.getElementById('username').value = '';
        if(username == ''){
            alert("Please enter Username");
        }else {
            getGithubInfo(username);
        }
}





