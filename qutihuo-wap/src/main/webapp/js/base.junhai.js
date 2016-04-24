(function() {
	// 定义JunHai类
	if (typeof JunHai == 'undefined') {
		// 声明框架命名空间
		JunHai = {
			version : '1.1 beta1',
			Biz:{},
			Base : {},
			Util : {},
			UI : {},
			Ajax : {}
		};
	}

	// 基础函数库
	JunHai.Base = {
		// 继承
		apply : function(C, D, B) {
			if (B) {
				JunHai.Base.apply(C, B);
			}
			if (C && D && typeof D == "object") {
				for (var A in D) {
					C[A] = D[A];
				}
			}
			return C;
		},
		// 继承
		applyIf : function(o, c) {
			if (o && c) {
				for (var p in c) {
					if (typeof o[p] == "undefined") {
						o[p] = c[p];
					}
				}
			}
			return o;
		},
		// 获取类型
		typeOf : function(v) {
            if (v === null) {
                return 'null';
            }
            var type = typeof v;
            if (type === 'undefined' || type === 'string' || type === 'number' || type === 'boolean') {
                return type;
            }
            var typeToString = toString.call(v);
            switch(typeToString) {
                case '[object Array]':
                    return 'array';
                case '[object Date]':
                    return 'date';
                case '[object Boolean]':
                    return 'boolean';
                case '[object Number]':
                    return 'number';
                case '[object RegExp]':
                    return 'regexp';
            }
            if (type === 'function') {
                return 'function';
            }
            if (type === 'object') {
                if (v.nodeType !== undefined) {
                    if (v.nodeType === 3) {
                        return (/\S/).test(v.nodeValue) ? 'textnode' : 'whitespace';
                    }
                    else {
                        return 'element';
                    }
                }
                return 'object';
            }
        }
	};

	// 全局变量、函数
//	var isOpera = $.browser.opera, isIE = $.browser.msie, isMoz = $.browser.mozilla;
//	if (isIE) {
//		try {
//			document.execCommand("BackgroundImageCache", false, true);
//		} catch (e) {}
//	}

	// String扩展去除空格
	String.prototype.trim = function(){
		var str = this;
		str = str.replace(/^\s\s*/, "");
		var ws = /\s/;
		var i = str.length;
		while (ws.test(str.charAt(--i)));
		return str.slice(0, i + 1);
	};
	// Array扩展是否在集合中
    Array.prototype.inArray = function(value){
        for (var i=0,l = this.length ; i < l ; i++) {
            if (this[i] === value) {
                return true;
            }
        }
        return false;
    };
	// Array删除
    Array.prototype.remove = function(value){
	    var newArr = this;
	    for(var i=0,count=newArr.length;i<count;){
	        if(newArr[i] == value){newArr.splice(i,1); count--;continue;}
	        i++;
	    }
	    return newArr;
	};
	Function.prototype.binding = function(){
	    if (arguments.length < 2 && typeof arguments[0] == "undefined") return this;
	    var __method = this, args = jQuery.makeArray(arguments), object = args.shift();
	    return function() {
	        return __method.apply(object, args.concat(jQuery.makeArray(arguments)));
	    }
	};

	// 工具辅助函数库
	JunHai.Util = {
		// 空字符串判断
		isEmpty : function(v, allowBlank) {
			return v === null || v === undefined || (!allowBlank ? v === "" : false);
		},
		// 空对象判断
		isNull : function(obj) {
			if (typeof obj == "undefined" || obj == null)
				return true;
			else
				return false;
		},
		// 取值空判断
		value : function(v, defaultValue, allowBlank) {
			return this.isEmpty(v, allowBlank) ? defaultValue : v;
		},
		// 数组类型判断
		isArray : function(v) {
			return v && typeof v.length == "number" && typeof v.splice == "function";
		},
		// 是否是整型
		isInteger : function(v){
			if(/^-?[0-9]\d*$/.test(v))
				return true;
			else
				return false;
		},
		// 数字类型
		isNumber: function(value) {
            return typeof value === 'number' && isFinite(value);
        },
		// 是否是中文
		isChinese : function(v){
			if (/^[\u4e00-\u9fa5]+$/.test(v))
				return true;
			else
				return false;
		},
		// 计算字符串长度
		strEllip : function(v){
			if(this.isEmpty(v)){
				return 0;
			}
			return v.replace(/[^\x00-\xff]/g,"**").length;
		},
		// 日期类型
		isDate : function(v){
			return toString.call(v) === '[object Date]';
		},
		// 对象类型
		isObject : function(v) {
            return toString.call(v) === '[object Object]';
        },
		// print Object
		inspect : function(obj){
			var s = obj + "\n";
			for (var a in obj) {
				if (typeof obj[a] != "function") {
					s += a + "=" + obj[a] + ",\n";
				}
			}
			alert("obj=" + s);
		},
		// 类似Java的Map
		Map : function(){
			// keys
			this.keys = new Array();
			// values
			this.data = new Object();

			JunHai.Base.apply(this,{
				// 放入一个键值对
				put : function(key, value) {
					if (this.data[key] == null) {
						this.keys.push(key);
					}
					this.data[key] = value;
				},
				// 获取某键对应的值
				get : function(key) {
					return this.data[key];
				},
				// 删除一个键值对
				remove : function(key) {
					this.keys.remove(key);
					this.data[key] = null;
				},
				// 遍历Map,执行处理函数 回调函数 function(key,value,index){..}
				each : function(fn) {
					if (typeof fn != 'function') {
						return;
					}
					var len = this.keys.length;
					for (var i = 0; i < len; i++) {
						var k = this.keys[i];
						fn(k, this.data[k], i);
					}
				},
				// 获取键值数组(类似Java的entrySet()) 键值对象{key,value}的数组
				entrys : function() {
					var len = this.keys.length;
					var entrys = new Array(len);
					for (var i = 0; i < len; i++) {
						entrys[i] = {
							key : this.keys[i],
							value : this.data[i]
						};
					}
					return entrys;
				},
				// key数组字符串
				keyArrString : function(separator){
					var keyArr = new Array();
					var size = this.size();
					this.each(function(key,value,index){
						if(!JunHai.Util.isNull(key)){
							if(size != index+1){
								keyArr.push(key);
								keyArr.push(separator);
							}else{
								keyArr.push(key);
							}
						}
					});
					return keyArr.join('');
				},
				// 仅value为string时调用
				valueArrString : function(separator){
					var valArr = new Array();
					var size = this.size();
					this.each(function(key,value,index){
						if(!JunHai.Util.isNull(value)){
							if(size != index+1){
								valArr.push(value);
								valArr.push(separator);
							}else{
								valArr.push(value);
							}
						}
					});
					return valArr.join('');
				},
				// 判断Map是否为空
				isEmpty : function() {
					return this.keys.length == 0;
				},
				//获取键值对数量
				size : function() {
					return this.keys.length;
				},
				valArray : function(){
					var valArr = new Array();
					this.each(function(key,value,index){
						if(!JunHai.Util.isNull(value)){
							valArr.push(value);
						}
					});
					return valArr;
				},
				//重写toString
				toString : function() {
					var s = "{";
					for (var i = 0; i < this.keys.length; i++, s += ',') {
						var k = this.keys[i];
						s += k + "=" + this.data[k];
					}
					s += "}";
					return s;
				}
			});
		},
		// 微型模板引擎
		tpl : function(str, data){
			var fn = !/\W/.test(str) ?
			  cache[str] = cache[str] ||
				JunHai.Util.tpl(document.getElementById(str).innerHTML) :
			  new Function("obj",
				"var p=[],print=function(){p.push.apply(p,arguments);};" +
				"with(obj){p.push('" +
				str.replace(/[\r\t\n]/g, " ")
				  .split("<%").join("\t")
				  .replace(/((^|%>)[^\t]*)'/g, "$1\r")
				  .replace(/\t=(.*?)%>/g, "',$1,'")
				  .split("\t").join("');")
				  .split("%>").join("p.push('")
				  .split("\r").join("\\'")
			  + "');}return p.join('');");
			return data ? fn( data ) : fn;
		}
	};

	// 提供页面端函数
	JunHai.UI = {
		// 获取Url参数
		getQueryParameter : function(qs){
			var s = location.href;
		    s = s.replace("?","?&").split("&");
		    var re = "";
		    for(i=1;i<s.length;i++)
		        if(s[i].indexOf(qs+"=")==0)
		            re = s[i].replace(qs+"=","");
		    return re;
		}
	};
	
	// 业务相关函数库
	JunHai.Biz = {
		
		// 浏览器工具
		WebToolkit : {
			// 添加收藏
			addFavorite : function(){
				var url="http://www.JunHai.com/";
				var title="骏海水产预定系统";
				ua = navigator.userAgent.toLowerCase();
				if(document.all){
					try{
						window.external.AddFavorite(url,title);
					}
					catch(e){
						alert("加入收藏失败，\n请您使用菜单栏或Ctrl+D收藏本站。");
					}
				}else if(window.sidebar){
					window.sidebar.addPanel(title,url,"")
				}
				else{
					alert("加入收藏失败，\n请您使用菜单栏或Ctrl+D收藏本站。");
				}
			},
			// 收藏首页
			setHomePage : function(){
				if (document.all){
						document.body.style.behavior='url(#default#homepage)';
						document.body.setHomePage('http://www.JunHai.com');
					}else if(window.sidebar){
						if(window.netscape){
							try{netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");}catch (e){
							alert( "亲爱的用户你好：\n你使用的不是IE浏览器，此操作被浏览器阻挡了，你可以选择手动设置为首页！\n给你带来的不便，本站深表歉意。" );
						}
					}
				}
			}
		},
		// Cookie操作
		cookie : function(name, value, options){
			if (typeof value != 'undefined') {
		        options = options || {};
		        if (value === null) {
		            value = '';
		            options.expires = -1;
		        }
		        var expires = '';
		        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
		            var date;
		            if (typeof options.expires == 'number') {
		                date = new Date();
		                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
		            } else {
		                date = options.expires;
		            }
		            expires = '; expires=' + date.toUTCString();
		        }
		        var path = options.path ? '; path=' + (options.path) : '';
		        var domain = options.domain ? '; domain=' + (options.domain) : '';
		        var secure = options.secure ? '; secure' : '';
		        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
		    } else {
		        var cookieValue = null;
		        if (document.cookie && document.cookie != '') {
		            var cookies = document.cookie.split(';');
		            for (var i = 0; i < cookies.length; i++) {
		                var cookie = jQuery.trim(cookies[i]);
		                if (cookie.substring(0, name.length + 1) == (name + '=')) {
		                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
		                    break;
		                }
		            }
		        }
		        return cookieValue;
		    }
	    }
	};

	// Ajax函数库
	JunHai.Ajax = {
		// Ajax请求替换DOM，并执行目标页面js,css
		do_request : function(el, url, params, callback){
			$.ajax({
				type : "POST",
				url : url,
				data : params,
				success : function(data){
					JunHai.Ajax.updateInnerHTML(el, data);
					if(callback){
						try {
							callback(data);
						}catch(e){
							//console.error("执行异步回调删除失败",e);
						}
					}
				}
			});
		},
		// 更新指定元素的innerHTML,并执行其中的script
		updateInnerHTML : function(id, html) {
			JunHai.Ajax._updateHTML(id, html, 'inner');
		},
		// 更新指定元素的innerHTML,并执行其中的script
		updateOuterHTML : function(id, html) {
			JunHai.Ajax._updateHTML(id, html, 'outer');
		},
		// script迭代器
		ScriptIterator : function() {
			this.elementArray = [];
			this.append = function(el) {
				this.elementArray.push(el);
			}
			this.hasNext = function() {
				return this.elementArray.length > 0;
			}
			this.next = function() {
				return this.elementArray.shift();
			}
		},
		// 更新指定元素的innerHTML,并执行其中的script
		_updateHTML : function(id, html, type) {
			if (typeof html == "undefined") {
				html = "";
			}
			var el = document.getElementById(id);
			if (!el) {
				//console.error("未找到ID为" + id + "的页面元素");;
			}
			// 容易造成IE崩溃
			html = JunHai.Ajax.loadLinkTags(html);
			html = JunHai.Ajax.loadStyleTags(html);
			var sIterator = new JunHai.Ajax.ScriptIterator();
			html = JunHai.Ajax.loadScriptTags(html, sIterator);
			if (type == 'inner') {
				el.innerHTML = html;
			} else if (type == 'outer') {
				el.outerHTML = html;
			}
			JunHai.Ajax.loadScripts(sIterator);
		},
		// 加载Link标签
		loadLinkTags : function(html) {
			var reLink = /(?:<link.*?\/(link)?>)/ig;
			var head = document.getElementsByTagName("head")[0];
			var match;
			while (match = reLink.exec(html)) {
				if (match && match[0]) {
					var link = document.createElement(match[0]);
					link.setAttribute('rel', 'stylesheet');
					link.setAttribute('type', 'text/css');
					head.appendChild(link);
				}
			}
			html = html.replace(/(?:<link.*?\/(link)?>)/ig, "");
			return html;
		},
		// 加载style标签
		loadStyleTags : function(html) {
			var reStyle = /(?:<style([^>]*)?>)((\n|\r|.)*?)(?:<\/style>)/ig;
			var match;
			var head = document.getElementsByTagName("head")[0];
			while (match = reStyle.exec(html)) {
				if (match[2] && match[2].length > 0) {
					var styleTag = document.createElement('style');
					styleTag.setAttribute('type', 'text/css');
					if (styleTag.styleSheet) {
						styleTag.styleSheet.cssText = match[2];
					} else {
						styleTag.appendChild(document.createTextNode(match[2]));
					}
					head.appendChild(styleTag);
				}
			}
			html = html.replace(/(?:<style.*?>)((\n|\r|.)*?)(?:<\/style>)/ig, "");
			return html;
		},
		//加载script标签
		loadScriptTags : function(html, sIterator) {
			var re = /(?:<script([^>]*)?>)((\n|\r|.)*?)(?:<\/script>)/ig;
			var srcRe = /\ssrc=([\'\"])(.*?)\1/i;
			var idRe = /\sid=([\'\"])(.*?)\1/i;
			var typeRe = /\stype=([\'\"])(.*?)\1/i;
			var match;
			while (match = re.exec(html)) {
				var attrs = match[1];
				var idMatch = attrs ? attrs.match(idRe) : false;
				if (idMatch && idMatch[2]) {
					var el = document.getElementById(idMatch[2]);
					if (el) {
						continue;
					}
				}
				var srcMatch = attrs ? attrs.match(srcRe) : false;
				if (srcMatch && srcMatch[2]) {
					var script = document.createElement("script");
					script.src = srcMatch[2];
					var typeMatch = attrs.match(typeRe);
					if (typeMatch && typeMatch[2]) {
						script.type = typeMatch[2];
					}
					sIterator.append({
						type : 1,
						script : script
					});
				} else if (match[2] && match[2].length > 0) {
					sIterator.append({
						type : 2,
						script : match[2]
					});
				}
			}
			html = html.replace(/(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig, "");
			return html;
		},
		// 加载多个script标签
		loadScripts : function(sIterator) {
			if (sIterator.hasNext()) {
				var el = sIterator.next();
				if (el) {
					if (el.type == 1) {
						var hd = document.getElementsByTagName("head")[0];
						var script = el.script;
						script.onload = script.onreadystatechange = function() {
							if (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete') {
								JunHai.Ajax.loadScripts(sIterator);
							}
						}
						hd.appendChild(script);
					} else if (el.type == 2) {
						if (window.execScript) {
							try {
								window.execScript(el.script);
							} catch (e) {
								//console.error(e.description);
							}
						} else {
							window.eval(el.script);
						}
						JunHai.Ajax.loadScripts(sIterator);
					}
				}
			}
		}
	}
})();

