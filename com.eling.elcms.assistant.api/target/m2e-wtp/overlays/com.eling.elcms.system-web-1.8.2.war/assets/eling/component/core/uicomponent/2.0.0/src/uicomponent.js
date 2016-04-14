/**
 * @name uicomponent
 * @version 2.0.0
 * @description ui组建基类
 * @dependencies ["base","tools","bootstrap"]
 */
define(function(require,exports,module){
	var Base = require("base");
	var tools = require("tools");
	var handlebars = require("handlebars");
	var theme = require("theme");
	var Templatable = require('templatable');
	require("bootstrap");
	
	var UIComponent = Base.extend({
		Implements : [Templatable],
		propsInAttrs: ["element", "events"],
		handlebars : handlebars,
        element: null,
        events: null,
        attrs: {
        	parentNode: "body",
            template: "<div></div>",
            model: {
            	theme : theme
            }
        },
        initialize: function(config) {
        	//1.初始化属性(不支持data-*这种属性的解析)
        	UIComponent.superclass.initialize.call(this,config);
            
            //2.解析模板
            this.parseElement();
            
            //3.初始化 events
            this.delegateEvents();
            
            //4.子类自定义的初始化
            this.setup();
        },
        // 构建 this.element
        parseElement: function() {
        	//检测是否有id，如果没有，则直接异常
        	var model = this.get("model");
        	if(!model.id){
        		model.id = new Date().getTime();
        	}
        	
        	this.initTheme();
        	
        	this.initCustAttr();
        	
        	var element = handlebars.compile(this.get("template"))(this.get("model"));
        	
            this.element = $(element);
        },
        // 注册事件代理
        delegateEvents: function() {
        	if (tools.isFunction(this.events)) {
                this.events = this.events();
            }
        	
            // key 为 'event selector'
            for (var key in this.events) {
                var args = key.split(" ");
                var eventType = args[0];
                var selector = args[1];
                (function(handler, widget) {
                    $(widget.element).on(eventType, selector, function(e){
                    	if (tools.isFunction(handler)) {
                            handler.call(widget, e);
                        } else {
                            widget[handler](e);
                        }
                    });
                })(this.events[key], this);
            }
            return this;
        },
        // 卸载事件代理
        undelegateEvents: function() {
            this.element && this.element.off();
            return this;
        },
        // 渲染（子类覆盖时，需保持 `return this`）
        render: function() {
            this.element.appendTo(this.get("parentNode"));
            this.afterRender();
            return this;
        },
        // 在 this.element 内寻找匹配节点
        $: function(selector) {
            return this.element.find(selector);
        },
        destroy: function() {
            this.undelegateEvents();
            // For memory leak
            if (this.element) {
                this.element.off();
                this.element.remove();
            }
            this.element = null;
            UIComponent.superclass.destroy.call(this);
        },
        //由子类复写
        initTheme : function(){
        	var model = this.get("model");
        	model.theme = theme.getTheme();
        },
        initCustAttr : function(){},
        setup: function(){
        	var autoRender=this.get("autoRender");
			if(autoRender==true){
				this.render();
			}
        },
        afterRender : function(){},
        hide : function(){
        	this.element.addClass("hidden");
        },
        show : function(){
        	this.element.removeClass("hidden");
        }
	});
	module.exports = UIComponent;
});
