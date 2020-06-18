$(function(){
	
    $("#eventbundle_event_image").hide();

	$("input[type=radio]").click(function(){

    
      var btn=$(this).val();
      if(btn=='garder')
      { 
      	$("#eventbundle_event_image").prop('type','text');
      	$("#eventbundle_event_image").attr('readonly',true);
         $("#eventbundle_event_image").show();
      }
      else if(btn=='modifier')
      { 
      	$("#eventbundle_event_image").prop('type','file');
        $("#eventbundle_event_image").show();
      }
         

	});
      


});