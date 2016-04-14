/**
 * 紧急呼救后处理
 */
define(function(require, exports, module) {
	var aw = require("ajaxwrapper");
    var ELView=require("elview");
    var Profile=require("profile");
    var enmu = require("enums");
    var Dialog=require("dialog");
	var Subnav = require("subnav"); 
	var store = require("store");
	var user = store.get("user");
	var Tab = require("tab");
	var DashBoard = require("dashboard");
    var handlebars = require("handlebars");
    var Grid = require("grid");
    var Editgrid = require("editgrid");
    var Form = require("form");
    var Daterange = require("daterange");
    var memberIndex = 0;
//    var template=require("./sosaftertreatment.tpl");
    var template=
    "<div class='J-subnav'></div>"+
    "<div class='J-grid'></div>"+
    "<div class='J-form hidden' ></div>"+
	"<div class='J-tab hidden' ></div>"/*+
	"<div class='J-contactsmsggird hidden' ></div>"+
	"<div class='J-sosloggird hidden' ></div>"*/; 
    var gridFetchProperties="";
	var sosaftertreatment = ELView.extend({
		attrs:{
        	template:template,
        	pkUser:user.pkUser
        },
        events : {
        	"click .nav-responsive li" : function(e){
        		var pkmember=$(e.target).attr("href").substring(7,8);
        		var members = this.get("members");
        		var widget = this;
        		if(members.length>1){
	        		for(var i;i<members.length;i++){
	        			if(pkmember==members[i].pkMember){
	        				widget.get("contactsmsggird").setData(members[i].contacts);
	        			}
	        		}
        		}
        		
        	}
        },
        initTab:function(members,flag){
        	var widget = this;
        	var items =[];
			for(var i = 0;i<members.length;i++){
				items.push({
					id:"member"+members[i].pkMember,
					title:members[i].personalInfo.name
				})
			}
			var tab = new Tab({
				parentNode:".J-tab",
				model:{
					items : items
				}
			});
			this.set("tab",tab);
			for(var i = 0;i<members.length;i++){
				widget.initMemberForm("member"+members[i].pkMember,members[i],flag);
			}
			if(members.length>0)
			{
				widget.get("contactsMsgGird").setData(members[0].contacts);
			}
        },
       /* initMemberForm:function(parentNode,member,flag){
        	var form_one=new Form({
        		 parentNode:"#"+parentNode,
        		 model:{ 
        			id: "f"+parentNode,
        			defaultButton:false,
        			items:[{
        				name:"pkMember",
						type:"hidden"
        			},{
        				name:"name",
						label:"姓名",
						className:{
    						container:"col-md-6",
    						label:"col-md-4"
    					},
						readonly:true,
        			},{
        				id:"radio"+parentNode,
                       name : "ismemberos",
                       type : "radio",
                       className:{
                    	  container:"col-md-6",
                    	  label:"col-md-4"
   					   },
                       label : "紧急呼救111",
                       list : [{
                           key : "True",
                           value : "是"
                       },{
                           key : "False",
                           value : "否"
                       }],
                       defaultValue:"False",
                   },{
                       name : "sex",
                       type : "radio",
                       label : "性别",
                       className:{
      						container:"col-md-6",
      						label:"col-md-4"
      					},
                       list : [{
                           key : "Male",
                           value : "男"
                       },{
                           key : "Female",
                           value : "女"
                       }],
                       defaultValue:"Male",
                   },{
                	   name:"birthday",
                	   format:"date",
                	   mode:"YYYY-MM-DD",
                	   type:"hidden",
                   },{
                   	name:"age",
					label:"年龄",
					className:{
	   						container:"col-md-6",
	   						label:"col-md-4"
	   				},
					readonly:true,
                   },{
                   	name:"mobilePhone",
					label:"手机号",
					className:{
  						container:"col-md-6",
  						label:"col-md-4"
  					},
					readonly:true,
                   },{
                   	name:"phone",
					label:"固定电话",
					className:{
  						container:"col-md-6",
  						label:"col-md-4"
  					},
					readonly:true,
                   }]
        		 }
			});
        	
			this.set(parentNode,form_one);
			this.get(parentNode).setData(member.personalInfo);
			this.get(parentNode).setValue("pkMember",member.pkMember);
			this.get(parentNode).setValue("age",moment().diff(member.personalInfo.birthday, 'years'));
			if(!flag){
				this.get(parentNode).setValue("ismemberos","True");
				this.get(parentNode).setDisabled("ismemberos",true);
			}else{
				this.get(parentNode).setValue("ismemberos","False");
				this.get(parentNode).setDisabled("ismemberos",false);
			}
			this.get(parentNode).setDisabled("sex",true);
        },*/
		initComponent:function(params,widget){
			var subnav=new Subnav({
				parentNode:".J-subnav",
				model:{
					title:"摄像头列表",
					items:[
					       /*{
						placeholder:"会员姓名",
						id : "search",
						type : "search",
						handler : function(str) {
							var g = widget.get("grid");
							var obj={
									"cno":subnav.getValue("cno"),
									s:str,
									properties:"member.personalInfo.name",
									fetchProperties:"pkRecord,version,member.pkMember,member.personalInfo.pkPersonalInfo,member.version,member.personalInfo.version," +
									"member.personalInfo.name," +
									"member.personalInfo.birthday," +
									"member.personalInfo.mobilePhone," +
									"member.personalInfo.phone," +
									"member.personalInfo.sex," +
									"member.personalInfo.contactAddresses.pkContactAddress," +
									"member.contacts.pkContacts," +
									"member.contacts.relation," +
									"member.contacts.personalInfo.name," +
									"member.contacts.sosContact," +
									"member.contacts.personalInfo.mobilePhone,"+
						    		"member.contacts.personalInfo.phone,"+
									"mobilePhone," +
									"sosTime," +
									"cno," +
									"sosStatus," +
									"sosAddress," +
									"member.personalInfo.contactAddresses.detailAddress,"+
									"member.personalInfo.contactAddresses.pkContactAddress," +
									"member.personalInfo.contactAddresses.communityData.name," +
									"member.personalInfo.contactAddresses.communityData.pkCommunityData," +
									"member.personalInfo.contactAddresses.buildingNumber," +
									"member.personalInfo.contactAddresses.unitNumber," +
									"member.personalInfo.contactAddresses.doorNumber," +
									"member.personalInfo.contactAddresses.address.*," +
									"member.personalInfo.contactAddresses.addressStatus.*," +
									"member.personalInfo.contactAddresses.version,"
									};
			        			var j=0;
			    				for(var i=0;i<user.roles.length;i++){
			    					if(user.roles[i].pkRole!=7){
			    						j++;
			    						break;
			    					}
			    				}
							aw.ajax({
								url:"api/monitor/search",
								data:obj,
								dataType:"json",
								success:function(data){
									g.setData(data);
								}
							});
						}
					},*//*{
						id:"cno",
						type:"buttongroup",
						all:{
							show:true,
							first:true
						},
						tip:"坐席",
						url : "api/attendant/query",
						keyField :"omni.cno",
						valueField:"omni.cno",
						params : function(){
							return {
								"commonUser.organization.pkOrganization":user.organization.pkOrganization,
								fetchProperties:"pkAttendant,version,omni.pkOmni,omni.version,omni.cno," +
								"commonUser.organization.pkOrganization," +
								"commonUser.organization.name," +
								"commonUser.operator.pkUser," +
								"commonUser.operator.name,"
							};
						},
						handler:function(key,element){
							widget.get("grid").refresh();
						}
					},*//*{
						id:"sosTime",
						type:"daterange",
						ranges : {
							"今天": [moment().startOf("days"),moment().endOf("days")],
					        "本月": [moment().startOf("month"), moment().endOf("month")]
						},
						defaultRange : "今天",
						minDate: "1930-05-31",
						maxDate: "2020-12-31",
						handler:function(){
							widget.get("grid").refresh();
						},
						tip : "呼救时间范围"
					},*//*{
						id : "close",
						type : "button",
						text:"误触关闭",
						show : false,
						handler:function(){
							aw.ajax({
								 url:"api/record/save",
								 data:{
									 "sosStatus":"Finished",
									 "recordLogs.content":"误触关闭",
									 "recordLogs.user.name":user.name,
									 "pkRecord":widget.get("form").getValue("pkRecord"),
									 "version":widget.get("form").getValue("version"),
									 fetchProperties:"pkRecord,version,recordLogs.pkRecordLog,recordLogs.version,"+
									 "sosStatus,recordLogs.user.name,recordLogs.manageTime,recordLogs.content"
								 },
								 dataType:"json",
								 success:function(data){
									 widget.get("grid").refresh();
								 }
							 });
							widget.show([".J-grid"]).hide([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
							widget.get("subnav").hide(["return","close"]).show(["cno","search","sosTime"]);
						}
					},{
						id:"dealfinish",
						type:"button",
						text:"处理完毕",
						show : false,
						handler:function(){
							aw.ajax({
								 url:"api/record/save",
								 data:{
									 "sosStatus":"Finished",
									 "pkRecord":widget.get("form").getValue("pkRecord"),
									 "version":widget.get("form").getValue("version"),
									 fetchProperties:"pkRecord,version,"+
									 "sosStatus"
								 },
								 dataType:"json",
								 success:function(data){
									widget.get("grid").refresh();
								 }
							 });
							widget.show([".J-grid"]).hide([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
							widget.get("subnav").hide(["return","dealfinish"]).show(["cno","search","sosTime"]);
						}
					},{
						id:"return",
						type:"button",
						text:"返回",
						show : false,
						handler:function(){
							widget.get("grid").refresh();
							widget.show([".J-grid"]).hide([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
							widget.get("subnav").hide(["return","dealfinish","close"]).show(["cno","search","sosTime"]);
						}
					}*/]
				}
			});
			this.set("subnav",subnav);
			//查询video
			var grid =new Grid({
				show:true,
			    parentNode:".J-grid",
			    url : "api/monitor/query",
			    params : function() {
			    	var subnav = widget.get("subnav");
			    	return{
			    		/*"sosTime" : subnav.getValue("sosTime").start,
    					"sosTimeEnd" : subnav.getValue("sosTime").end,*/
    					/*orderString : "sosTime:desc",*/
			    		//fetchProperties:"pkMonitor,name,ip,port,account,password,description,base64UrlSub,base64Urlmain,serverUrl"
			    		fetchProperties:"monitor.name,monitor.ip,monitor.port,monitor.password,monitor.description,base64Urlmain,serverUrl"
			    		/*"pkRecord,version,member.pkMember,member.personalInfo.pkPersonalInfo,member.version,member.personalInfo.version," +
			    		"member.personalInfo.name," +
			    		"member.personalInfo.mobilePhone," +
			    		"member.personalInfo.phone," +
			    		"member.personalInfo.sex," +
			    		"member.personalInfo.birthday," +
			    		"member.contacts.pkContact," +
			    		"member.personalInfo.contactAddresses.pkContactAddress," +
			    		"member.contacts.pkContacts,member.contacts.relation," +
			    		"member.contacts.personalInfo.name," +
			    		"member.contacts.sosContact," +
			    		"member.contacts.personalInfo.mobilePhone,"+
			    		"member.contacts.personalInfo.phone,"+
						"mobilePhone," +
						"sosTime," +
						"cno," +
						"sosStatus," +
						"member.personalInfo.contactAddresses.detailAddress," +
						"recordLogs.user.pkUser," +
						"recordLogs.user.version," +
						"recordLogs.pkRecordLog," +
						"recordLogs.content,recordLogs.manageTime," +
						"sosAddress," +
						"recordLogs.version," +
						"recordLogs.user.name"*/
			    	}
			    },
			    model:{
			    	columns : [{
			    		name:"monitor.name",
						label:"摄像头名"
			    	},
			    	{
			    		name:"monitor.description",
						label:"描述"
			    	},/*{
			    		name:"ip",
						label:"IP"
			    	},{
			    		name:"port",
			    		label:"端口"
			    	},{
			    		name:"account",
						label:"用户名"
			    	},{
			    		name:"password",
						label:"密码"
			    	},*/{
			    		name:"operate",
						label:"操作",
						format:"button",
						formatparams:[/*{
							id:"deal",
							text:"处理",
							show:function(value,row){
								if((row.sosStatus.key!="Finished")){
									return true;
								}else{
									return false;
								}
							},
							handler:function(index,data,rowEle){
								widget.get("tab") ? widget.get("tab").destroy() : true;
								var members = [];
								alert(11);
								if(data.member!=null){
									members[0] = data.member;
									widget.initTab(members,false);
								}
								widget.get("form").reset();
								widget.get("form").setData(data);
								widget.get("form").setDisabled(true);
								if(data.sosStatus.key=="Finished"){
									widget.get("sosLogGird").setDisabled(true);
								}else{
									widget.get("sosLogGird").setDisabled(false);
								}
								widget.get("sosLogGird").setData(data.recordLogs);
								widget.get("sosLogGird").refresh();
								widget.hide([".J-grid"]).show([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
								widget.get("subnav").show(["return","dealfinish"]).hide(["cno","search","sosTime","close"]);
								}
						},*/{
							id:"detail",
							text:"查看",
							handler:function(index,data,rowEle){
								 if (window.innerWidth)
								winWidth = window.innerWidth;
								else if ((document.body) && (document.body.clientWidth))
								winWidth = document.body.clientWidth;
								leftWidth=(winWidth-500)/2;
								//iframe pass 无法最大化
								Dialog.confirm({
				                    title : data.monitor.name,
				                    //content : "<iframe src='allowscriptaccess='always' allowTransparency='true' http://117.121.26.96:51132/example/assets/eling/component/utils/dialog/2.0.0/test/demo.htm' width='100%' height='400px' frameborder='no'></iframe>",
				                    content : "<iframe allowfullscreen='true' webkitallowfullscreen='true' mozallowfullscreen='true' src='http://"+data.serverUrl+"/video/player/"+data.base64UrlSub+".do' width='100%' height='300px' frameborder='no'></iframe>",
					                    setStyle : function(){
				                        $(".el-dialog-modal .modal").css({
				                            top : "7%",
				                            /*width:"1200",
				                            height:"900",*/
				                            width : 550,
				                            left  : leftWidth
				                        });

				                        $(".J-dialog-cancel").css({
				                            display : "none"
				                        });

				                        $(".el-dialog-modal .modal-body pre").css({
				                            "padding": "0 9.5px",
				                            "margin-bottom": "0"
				                        });
				                        
				                    }
								
				                });
								$("#videojsframe").attr("allowfullscreen","true");
								$(".J-dialog-confirm").text("关闭");
								$(".J-dialog-confirm").addClass("btn btn-danger J-dialog-confirm");
								/*
								widget.get("tab") ? widget.get("tab").destroy() : true;
								var members = [];
								alert(12);
								if(data.member!=null){
									members[0] = data.member;
									widget.initTab(members,false);
								}
								widget.get("form").reset();
								widget.get("form").setData(data);
								widget.get("form").setDisabled(true);
								widget.get("sosLogGird").setDisabled(true);
								widget.get("sosLogGird").setData(data.recordLogs);
								widget.get("sosLogGird").refresh();
								widget.hide([".J-grid"]).show([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
								widget.get("subnav").show(["return"]).hide(["cno","search","sosTime","close","dealfinish"]);*/
								}
						}]
			    	}]
			    }
			});
			this.set("grid",grid);
			//紧急呼救Form
			/*var form =new Form({
				show:true,
			    parentNode:".J-form",
				 model:{
					defaultButton:false,
					id:"sosform",
					items:[{
						name:"pkRecord",
						type:"hidden"
					},{
						name : "version",
						defaultValue : "0",
						type : "hidden"
					},{
						name:"sosStatus",
						type:"hidden",
						defaultValue : "OnGoing",
					},{
						name:"mobilePhone",
						label:"紧急呼救号码",
						readonly:true
					},{
						name:"sosTime",
						label:"呼救时间",
						type:"date",
						mode:"YYYY-MM-DD HH:mm:ss",
						readonly:true
					},{
						name:"sosAddress",
						label:"地址",
						readonly:true,
					}]
				 }
			});
			this.set("form",form);*/
			
			/*//联系人信息
			var contactsMsgGird = new Grid({
				parentNode:".J-contactsmsggird",
				autoRender : false,
				model : {
					head : {
						title : "联系人信息",
                    },
					columns : [{
						name : "relation",
						label : "与会员关系",
					},{
						name : "personalInfo.name",
						label : "姓名",
					},{
						name : "sosContact",
						label : "是否为紧急联系人",
						format:function(value,row){
							if(value==true){
								return "是";
							}else{
								return "否";
							}
						}
						
					},{
						name : "personalInfo.mobilePhone",
						label : "电话",
					},{
						name : "personalInfo.phone",
						label : "固定电话",
					}]
				}
			});
			this.set("contactsMsgGird",contactsMsgGird);*/
			//处理日志网格
			/*var sosLogGird = new Editgrid({
				parentNode : ".J-sosloggird",
				url : "api/monitor1/query",
				autoRender:false,
				params : function() {
					var subnav=widget.get("subnav");
					var pkRecord = widget.get("form").getValue("pkRecord");
    				return {
    					sos:pkRecord,
    					orderString : "manageTime:desc",
    					fetchProperties:"pkRecordLog,version,user.pkUser,user.name,record.pkRecord,"+
    					"manageTime,content,"+
    					"record.member.pkMember," +
    					"record.sosAddress," +
    					"record.sosTime," +
    					"record.mobilePhone," +
    					"record.cno," +
    					"record.sosStatus,"+
    					"record.member.personalInfo.pkPersonalInfo," +
    					"record.member.personalInfo.name," +
    					"record.member.personalInfo.birthday,"+
    					"record.member.personalInfo.mobilePhone," +
						"record.member.personalInfo.phone," +
						"record.member.personalInfo.sex," +
    					"record.member.contacts.pkContact," +
    					"record.member.personalInfo.contactAddresses.pkContactAddress," +
						"record.member.personalInfo.contactAddresses.communityData.name," +
						"record.member.personalInfo.contactAddresses.communityData.pkCommunityData," +
						"record.member.personalInfo.contactAddresses.buildingNumber," +
						"record.member.personalInfo.contactAddresses.unitNumber," +
						"record.member.personalInfo.contactAddresses.doorNumber," +
						"record.member.personalInfo.contactAddresses.address.*," +
						"record.member.personalInfo.contactAddresses.addressStatus.*," +
						"record.member.personalInfo.contactAddresses.version"
        				}
    			},
				model : {
					allowFoot : false,
					id:"sosLogGird",
					className:"col-md-2",
					head : {
						title : "处理日志",
                        buttons : [{
                            id : "savelog",
                            text:"日志保存",
                            handler : function(){
                            	var saveData = {};
                            	var gridData=widget.get("sosLogGird").getData();
                            	var form=widget.get("form");
                            	var sosAddress=form.getValue("sosAddress");
                            	if(gridData.length<1){
                            		Dialog.alert({
                            			content:"数据为空，不能保存"
                            		})
                            		return false;
                            	}
                            	var temp = $.extend(true,[],gridData);
                            	for(var i=0;i<temp.length;i++){
                            		if(temp[i].user.pkUser){
                            			temp[i].user=temp[i].user.pkUser;
                            		}
                            		temp[i].sos = widget.get("form").getValue("pkRecord");
                            	}
                            	var radio=$("input:radio[name='ismemberos']:checked");
                            	for(var i = 0;i<radio.length;i++){
                            		if($(radio[i]).val() == "True"){
                            			var divId=$(radio[i]).parents(".tab-pane").attr("id");
                                		var memberId=divId.substring(6,divId.length);
                                		saveData.pkMember=memberId;
                            		}
                            	}
                            	saveData.recordLogs=temp;
                            	saveData.sosAddress = sosAddress;
                            	saveData.pkRecord = widget.get("form").getValue("pkRecord");
                            	aw.saveOrUpdate("api/monitor1/query",aw.customParam(saveData),function(data){
                            		Dialog.tip("保存成功");
                            		widget.get("sosLogGird").refresh();
        	          			});
                            }
                        }]
                    },
					columns : [{
						name : "user.name",
						label : "处理人",
						allowEdit : false,
						format :function(value,row){
							return row.user.name;
  						}
					},{
						name:"manageTime",
						format:"date",
						className:"col-md-4",
						label:"处理时间",
						formatparams : {
							mode : "YYYY-MM-DD HH:mm"
						},
					},{
						name:"content",
						label:"内容",
						className:"col-md-4",
						editor : {
							type : "text",
							onChange : function(plugin,index,rowData){
								rowData.content = plugin.getValue();
								if(plugin.getValue().length>255){
									Dialog.alert({content:"内容不超过255字！"});
									return;
								}
									
								sosLogGird.update(index,rowData)
							},
							onAdd : function(editors){
								var content=editors["content"];
								if(content.length>255){
									Dialog.alert({content:"内容不超过255字！"});
									return;
								}
								var pkUser = user.pkUser;
								var pkRecord = widget.get("form").getValue("pkRecord");
								sosLogGird.add({
									user : pkUser,
									manageTime:moment()._i,
									content:content.getValue()
								});
							  },
						}
					},{
						name:"operate",
						label:"操作",
						className:"col-md-1",
						format:"button",
						formatparams:[{
							id:"delete",	
							text:"删除",
							handler:function(index,data,rowEle){
								Dialog.confirm({
  									content : "确认删除该条数据吗？",
  									confirm : function(){
  										sosLogGird.remove(rowEle);
  									},
  									cancel : function(){
  										Dialog.close;
  									}
  								});
								aw.del("api/recordlog/" + data.pkRecordLog + "/delete",function() {
										sosLogGird.refresh();
								});
							}
						}]
					}]
				}
			});
			this.set("sosLogGird",sosLogGird);*/
		  },
		 /* sosLogAdd:function(editors,sosLogGrid){
			  var widget = this;
			  var user = editors["user.name"];
				var manageTime=editors["manageTime"];
				var content=editors["content"];
				if(manageTime.getValue()!=null &&manageTime.getValue()!=""&&content.getValue()!=null&&content.getValue()!=""){
					widget.get("sosLogGird").add({
						"user.name" : user.name,
						manageTime:manageTime.getValue(),
						content:content.getValue()
					});
				}
		  },*/
		/* afterInitComponent:function(params,widget){
			 if(params&& params.father && params.father == "sos"){
				 var pkMemberIn = params.pkMemberIn;
				 var pkRecord = params.pkRecord;
				 widget.get("tab") ? widget.get("tab").destroy() : true;
				 aw.ajax({
					 url:"api/monitor1/query",
					 data:{
						 pkRecord:pkRecord,
						 fetchProperties:"pkRecord,version,"+
						 "mobilePhone,sosTime"
					 },
					 dataType:"json",
					 success:function(data){
						 widget.get("form").setData(data[0]);
					 }
				 });
				 aw.ajax({
					 url:"api/monitor1/query",
					 data:{
						 pkMemberIn:pkMemberIn,
						 fetchProperties:"pkMember,version,personalInfo.pkPersonalInfo,personalInfo.name," +
						 "personalInfo.sex,personalInfo.birthday," +
						 "personalInfo.contactAddresses.pkContactAddress,"+
						 "personalInfo.phone,personalInfo.mobilePhone," +
						 "personalInfo.contactAddresses.detailAddress,"+
						 "contacts.pkContacts," +
						 "contacts.version," +
						 "contacts.relation," +
						 "contacts.personalInfo.pkPersonalInfo," +
						 "contacts.personalInfo.name," +
						 "contacts.sosContact," +
						 "contacts.personalInfo.mobilePhone," +
						 "personalInfo.contactAddresses.pkContactAddress," +
						 "personalInfo.contactAddresses.communityData.name," +
						 "personalInfo.contactAddresses.communityData.pkCommunityData," +
						 "personalInfo.contactAddresses.buildingNumber," +
						 "personalInfo.contactAddresses.unitNumber," +
						 "personalInfo.contactAddresses.doorNumber," +
						 "personalInfo.contactAddresses.address.*,personalInfo.contactAddresses.deleteFlag," +
						 "personalInfo.contactAddresses.addressStatus.*," +
						 "personalInfo.contactAddresses.version," +
						 "contacts.personalInfo.phone"
					 },
					 dataType:"json",
					 success:function(data){
						 alert(data);
						 widget.set("members",data);
						 widget.initTab(data,true);
						 var address = data[0].personalInfo.contactAddresses;
						 for(var i =0 ; i<address.length;i++){
							 if(!address[i].deleteFlag&&address[i].addressStatus.key == "DefaultAddress"){
								 var addres = address[i].address.fullName+" "+address[i].communityData.name+address[i].buildingNumber+"号楼"+name+address[i].unitNumber+"单元"+name+address[i].doorNumber;
								 widget.get("form").setValue("sosAddress",addres);
							 }
						}
						 widget.hide([".J-grid"]).show([".J-form",".J-tab",".J-contactsmsggird",".J-sosloggird"]);
						 widget.get("subnav").show(["return","close"]).hide(["cno","search","sosTime"]);
					 }
				 })
			 }
	    }*/
	});
	module.exports = sosaftertreatment;
});
