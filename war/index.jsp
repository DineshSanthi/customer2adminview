
<script>
	var repo = window.repo ? window.repo : {};
	repo.serviceUrl = "http://springbootapplication-customer2.192.168.99.100.nip.io/";
	
	repo.objectDefinition = {};
	
	repo.objectDefinition["application"] = {
			"id":{datatype: "STRING", label: "ID", hidden:true},
			"appName":{datatype: "STRING", label: "Application Name", hidden:true},
			"appDescrption":{datatype: "STRING", label: "Description", hidden:true}		
	};

	repo.objectDefinition["table"] = {
			"id":{datatype: "STRING", label: "ID", hidden:true},
			"tableName":{datatype: "STRING", label: "Table Name", hidden:true},
			"tableDescrption":{datatype: "STRING", label: "Description", hidden:true}		
	};	
	
</script>

<html>
 <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Repo Depo - Admin</title>
    <meta name="viewport" content="initial-scale=1, width=device-width, user-scalable=no, minimum-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <!--CSS for loading message at application Startup-->
    <style type="text/css">
        html, body { overflow:hidden }
        #loadingWrapper {
            position: absolute;
            top: 40%;
            width: 100%;
            text-align: center;
            z-index: 900001;
        }
        #loading {
            margin: 0 auto;
            border: 1px solid #ccc;
            width: 160px;
            padding: 2px;
            text-align: left;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }
    </style>
    <link rel="stylesheet" href="RepoDepo.css?isc_version=12.0p_2018-06-30.css">
    
</head>
<body>
<iframe id="__gwt_historyFrame" style="width:0;height:0;border:0"></iframe>

<!--add loading indicator while the app is being loaded-->
<div id="loadingWrapper">
<div id="loading">
    <div class="loadingIndicator">
        <!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>SmartGWT<br/>-->
        <img src="images/loading.gif" width="16" height="16" style="margin-right:8px;float:left;vertical-align:top;"/>Repo Depo<br/>
        <span id="loadingMsg">Loading styles and images...</span></div>
</div>
</div>

<!-- IMPORTANT : You must set the variable isomorphicDir to [MODULE_NAME]/sc/ so that the Smart GWT
     resource are correctly resolved -->
<script type="text/javascript">
var isomorphicDir = "helloworld/sc/";
document.getElementById('loadingMsg').innerHTML = 'Loading Core API...';
</script>
<script src="helloworld/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<!--include the SC Core API-->
<script type="text/javascript" src='helloworld/sc/modules/ISC_Core.js?isc_version=12.0p_2018-06-30.js'></script>

<!--include SmartClient -->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading UI Components...';</script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Foundation.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Containers.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Grids.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Forms.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_RichTextEditor.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Data API...';</script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_DataBinding.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Calendar.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Drawing.js?isc_version=12.0p_2018-06-30.js'></script>


	<!-- ACE - main .js file -->
	<script src="ace/ace.js" type="text/javascript" charset="utf-8"></script>
	<!-- Get .js files for any needed ACE modes and themes -->
	<script src="ace/theme-eclipse.js" type="text/javascript" charset="utf-8"></script>
	<script src="ace/mode-java.js" type="text/javascript" charset="utf-8"></script>
	<script src="ace/theme-twilight.js" type="text/javascript" charset="utf-8"></script>
	<script src="ace/mode-perl.js" type="text/javascript" charset="utf-8"></script>
	<!--  Needed for autocompletion support. -->
	<script src="ace/ext-language_tools.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

// Determine what skin file to load
var currentSkin = readCookie('skin_name_2_4');
if (currentSkin == null) currentSkin = isc.Browser.defaultSkin;
</script>

<!--load skin-->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading skin...';</script>

<script type="text/javascript">
document.write("<"+"script src=helloworld/sc/skins/" + currentSkin + "/load_skin.js?isc_version=12.0p_2018-06-30.js><"+"/script>");
</script>

<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Application<br>Please wait...';</script>

<script type="text/javascript">document.getElementById('loading').remove();</script>

<!--include the application JS-->
<script type="text/javascript" src="helloworld/helloworld.nocache.js"></script>

</body>
</html>