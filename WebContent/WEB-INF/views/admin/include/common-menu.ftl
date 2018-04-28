<a class="menu-toggler" id="menu-toggler" href="#">
    <span class="menu-text"></span>
</a>

<div class="sidebar" id="sidebar">
    <ul class="nav nav-list">
    <#if sessionUser.type == 'admin'>
        <li>
            <a href="/admin/user/list"><i class="icon-user"></i>用户管理</a>
        </li>
        <li>
            <a href="/admin/notice/list"><i class="icon-list"></i>公告管理</a>
        </li>
        <li>
            <a href="#" class="dropdown-toggle"><i class="icon-home"></i><span class="menu-text">房屋管理</span></a>

            <ul class="submenu">
                <li>
                    <a href="/admin/house/list?type=出售">
                        <i class="icon-double-angle-right"></i>
                        出售信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=出租">
                        <i class="icon-double-angle-right"></i>
                        出租信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=求售">
                        <i class="icon-double-angle-right"></i>
                        求售信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=求租">
                        <i class="icon-double-angle-right"></i>
                        求租信息
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#" class="dropdown-toggle"><i class="icon-home"></i><span class="menu-text">预约看房</span></a>

            <ul class="submenu">
                <li>
                    <a href="/admin/order/list?flag=2">
                        <i class="icon-double-angle-right"></i>
                        买房
                    </a>
                </li>
                <li>
                    <a href="/admin/order/list?flag=1">
                        <i class="icon-double-angle-right"></i>
                        租房
                    </a>
                </li>

            </ul>
        </li>
        <li>
            <a href="/admin/comment/list"><i class="icon-book"></i>评论管理</a>
        </li>
    </#if>

    <#if sessionUser.type == 'user'>
        <li>
            <a href="/admin/info"><i class="icon-user"></i>个人信息</a>
        </li>
        <li>
            <a href="#" class="dropdown-toggle"><i class="icon-home"></i><span class="menu-text">我发布的</span></a>

            <ul class="submenu">
                <li>
                    <a href="/admin/house/list?type=出售">
                        <i class="icon-double-angle-right"></i>
                        出售信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=出租">
                        <i class="icon-double-angle-right"></i>
                        出租信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=求售">
                        <i class="icon-double-angle-right"></i>
                        求售信息
                    </a>
                </li>
                <li>
                    <a href="/admin/house/list?type=求租">
                        <i class="icon-double-angle-right"></i>
                        求租信息
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="/admin/comment/list"><i class="icon-book"></i>我发表的评论</a>
        </li>
    </#if>

    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
           data-icon2="icon-double-angle-right"></i>
    </div>
</div>
