function upDate(previewPic) {
    console.log(previewPic);
    $('#image').css('background-image', 'url(' + previewPic.src + ')'); // to add the image while mouse hovering
    $('#image').html(previewPic.alt); //to add the alternate text

}
function unDo() {
    $('#image').css('background-image','none'); //for no background image
    $('#image').html("Place mouse on below images to show here.");//text after mouse removed
}


