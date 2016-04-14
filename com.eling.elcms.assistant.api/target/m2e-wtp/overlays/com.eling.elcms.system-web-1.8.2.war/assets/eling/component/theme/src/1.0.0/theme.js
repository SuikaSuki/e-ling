define(function(require,exports,module){
	var store=require("store");
	var theme={
		eling:{
			bgClass:"contrast-red",
			bgColor:"#f34541",
			logo_left:"assets/eling/resources/logo_el.png",
			logo_left_xs:"assets/eling/resources/logo_el_xs.png",
			favicon:"assets/eling/resources/meta_icons/favicon1.ico"
		},
		sn:{
			bgClass:"contrast-brown",
			bgColor:"#924e31",
			logo_left:"assets/eling/resources/logo_el.png",
			logo_left_xs:"assets/eling/resources/logo_el_xs.png",
			favicon:"assets/eling/resources/meta_icons/favicon1.ico"
		},
		rh:{
			bgClass:"contrast-blue",
			bgColor:"#00acec",
			logo_left:"assets/eling/resources/logo_el.png",
			logo_left_xs:"assets/eling/resources/logo_el_xs.png",
			favicon:"assets/eling/resources/meta_icons/favicon1.ico"
		},
		qlh:{
			bgClass:"contrast-brown",
			bgColor:"#924e31",
			logo_left:"assets/eling/resources/logo_qlh_ind.png",
			logo_left_xs:"assets/eling/resources/logo_qlh_ind.png",
			favicon:"assets/eling/resources/logo_qlh_ico.png"
		},
		ftn:{
			bgClass:"contrast-ftn",
			bgColor:"rgb(221,73,35)",
			logo_left:"assets/eling/resources/logo_ftn.png",
			logo_left_xs:"assets/eling/resources/logo_ftn.png",
			favicon:"assets/eling/resources/logo_ftn.png",
			width:100,
			height:38
		}
	};
	
	var ThemeUtils={
		init:function(homeName,themeName){
			//设置背景颜色
			$(".J-bgColor").addClass(theme[themeName].bgClass).removeClass("hidden");
			
			//设置主题
			store.set("home",homeName);
			var themeObject=theme[themeName];
			store.set("theme"+homeName,themeObject);
		},
		getThemeAttr:function(attr){
			var theme=this.getTheme() || {};
			return theme[attr] || "";
		},
		getTheme:function(){
			var home=store.get("home");
			return store.get("theme"+home);
		}
	};
	
	module.exports=ThemeUtils;
});