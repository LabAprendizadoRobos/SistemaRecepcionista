// Faz com que o Kinect examine o que observa para
// detectar a presença de uma pessoa, com intervalos
// de tempo constantes.

// $(window).on('load', callDetect);

// window.setInterval(callDetect, 10*1000);

function callDetect(){
    $.ajax({
            url:'Detect',
            data:{},
            type:'get',
            cache:false,
            success:function(data){
        },
        error:function(){
            alert('error');
        }
    });
}

