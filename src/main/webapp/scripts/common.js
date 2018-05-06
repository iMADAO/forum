	
	$(function(){
		alert("Hello");
		$("#reset").click(function(){
			alert("Reset");
//			$(":enable :not(':submit')").val("");
//			$(".empty").remove();
//			this.attr("disabled", "disabled");

		});
		
		$("#submit").click(function(){
			$(".empty").remove();
			$form = $(".form");
			var flag = true;
			for(var i=0; i<$form.length; i++){
				var value = $form[i].value;
				value = $.trim(value);
				if(value==""){
					flag = false;
					$($form[i]).after("<span class='empty'>²»ÄÜÎª¿Õ</span>");					
				}
			}
			
			if(flag==false){
				return false;
			}
		})
	})
