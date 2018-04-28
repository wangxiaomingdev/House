<!DOCTYPE html>
<html lang="en">
<#include "../include/resource-head.ftl"/>
<#assign active='configList'/>
<body>
<#include "../include/common-top.ftl"/>
<div class="main-container" id="main-container">
    <div class="main-container-inner">
    <#include "../include/common-menu.ftl"/>
        <div class="main-content">
            <div class="page-content">
                <div class="col-xs-12">
                    <div class="row-fluid">
                        <div class="widget-box">
                            <div class="widget-header widget-header-blue widget-header-flat">
                                <h4 class="lighter">
                                    <i class="icon-plus"></i>配置详情
                                </h4>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="step-content row-fluid position-relative">
                                        <form class="form-horizontal" action="/admin/config/update" method="post">
                                            <input name="id" type="hidden" value="${config.id}">
                                            <div class="form-group has-info">
                                                <label class="col-xs-12 col-sm-3 control-label no-padding-right">名字</label>
                                                <div class="col-xs-12 col-sm-5">
                                                    <input type="text" class="width-100" value="${config.name!}" readonly/>
                                                </div>
                                            </div>
                                            <div class="form-group has-info">
                                                <label class="col-xs-12 col-sm-3 control-label no-padding-right">标识</label>
                                                <div class="col-xs-5 col-sm-5">
                                                    <input type="text" class="width-100" value="${config.nid!}" readonly/>
                                                </div>
                                            </div>
                                            <div class="form-group has-info">
                                                <label class="col-xs-12 col-sm-3 control-label no-padding-right">描述</label>
                                                <div class="col-xs-12 col-sm-5">
                                                    <textarea class="autosize-transition form-control" required name="value">${config.value!}</textarea>
                                                </div>
                                            </div>
                                            <hr/>
                                            <div class="row-fluid wizard-actions">
                                                <button class="btn" type="reset">重置</button>
                                                <button class="btn btn-success" type="submit">提交</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
</body>
<#include "../include/resource-script.ftl"/>
</html>

