-- 创建数据库
CREATE DATABASE IF NOT EXISTS "pms-admin" WITH ENCODING = 'UTF8';

-- 使用数据库
\c pms-admin;

-- 创建用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    real_name VARCHAR(50),
    avatar VARCHAR(255),
    status INTEGER NOT NULL DEFAULT 1,
    last_login_time TIMESTAMP,
    last_login_ip VARCHAR(50),
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0
);

-- 创建角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    role_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    status INTEGER NOT NULL DEFAULT 1,
    sort_order INTEGER DEFAULT 0,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0
);

-- 创建权限表
CREATE TABLE IF NOT EXISTS sys_permission (
    id BIGSERIAL PRIMARY KEY,
    permission_name VARCHAR(100) NOT NULL,
    permission_code VARCHAR(100) NOT NULL UNIQUE,
    permission_type VARCHAR(20) NOT NULL,
    parent_id BIGINT,
    path VARCHAR(200),
    component VARCHAR(200),
    icon VARCHAR(50),
    description VARCHAR(200),
    status INTEGER NOT NULL DEFAULT 1,
    sort_order INTEGER DEFAULT 0,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0
);

-- 创建用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0,
    UNIQUE(user_id, role_id)
);

-- 创建角色权限关联表
CREATE TABLE IF NOT EXISTS sys_role_permission (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0,
    UNIQUE(role_id, permission_id)
);

-- 创建部门表
CREATE TABLE IF NOT EXISTS sys_dept (
    id BIGSERIAL PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    dept_code VARCHAR(50) UNIQUE,
    parent_id BIGINT,
    ancestors VARCHAR(500),
    leader VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    status INTEGER NOT NULL DEFAULT 1,
    sort_order INTEGER DEFAULT 0,
    description VARCHAR(200),
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0
);

-- 创建菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id BIGSERIAL PRIMARY KEY,
    menu_name VARCHAR(100) NOT NULL,
    menu_code VARCHAR(100) UNIQUE,
    parent_id BIGINT,
    menu_type VARCHAR(20) NOT NULL,
    path VARCHAR(200),
    component VARCHAR(200),
    perms VARCHAR(100),
    icon VARCHAR(50),
    visible INTEGER NOT NULL DEFAULT 1,
    status INTEGER NOT NULL DEFAULT 1,
    sort_order INTEGER DEFAULT 0,
    is_frame INTEGER NOT NULL DEFAULT 0,
    is_cache INTEGER NOT NULL DEFAULT 0,
    description VARCHAR(200),
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0
);

-- 创建用户部门关联表
CREATE TABLE IF NOT EXISTS sys_user_dept (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    dept_id BIGINT NOT NULL,
    is_primary INTEGER NOT NULL DEFAULT 0,
    position VARCHAR(50),
    join_date TIMESTAMP,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0,
    UNIQUE(user_id, dept_id)
);

-- 创建角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    is_deleted INTEGER DEFAULT 0,
    UNIQUE(role_id, menu_id)
);

-- 创建索引
CREATE INDEX IF NOT EXISTS idx_user_username ON sys_user(username);
CREATE INDEX IF NOT EXISTS idx_user_email ON sys_user(email);
CREATE INDEX IF NOT EXISTS idx_user_status ON sys_user(status);
CREATE INDEX IF NOT EXISTS idx_user_dept_id ON sys_user(dept_id);
CREATE INDEX IF NOT EXISTS idx_role_role_code ON sys_role(role_code);
CREATE INDEX IF NOT EXISTS idx_role_status ON sys_role(status);
CREATE INDEX IF NOT EXISTS idx_permission_permission_code ON sys_permission(permission_code);
CREATE INDEX IF NOT EXISTS idx_permission_parent_id ON sys_permission(parent_id);
CREATE INDEX IF NOT EXISTS idx_permission_status ON sys_permission(status);
CREATE INDEX IF NOT EXISTS idx_user_role_user_id ON sys_user_role(user_id);
CREATE INDEX IF NOT EXISTS idx_user_role_role_id ON sys_user_role(role_id);
CREATE INDEX IF NOT EXISTS idx_role_permission_role_id ON sys_role_permission(role_id);
CREATE INDEX IF NOT EXISTS idx_role_permission_permission_id ON sys_role_permission(permission_id);
CREATE INDEX IF NOT EXISTS idx_dept_dept_code ON sys_dept(dept_code);
CREATE INDEX IF NOT EXISTS idx_dept_parent_id ON sys_dept(parent_id);
CREATE INDEX IF NOT EXISTS idx_dept_status ON sys_dept(status);
CREATE INDEX IF NOT EXISTS idx_menu_menu_code ON sys_menu(menu_code);
CREATE INDEX IF NOT EXISTS idx_menu_parent_id ON sys_menu(parent_id);
CREATE INDEX IF NOT EXISTS idx_menu_menu_type ON sys_menu(menu_type);
CREATE INDEX IF NOT EXISTS idx_menu_status ON sys_menu(status);
CREATE INDEX IF NOT EXISTS idx_user_dept_user_id ON sys_user_dept(user_id);
CREATE INDEX IF NOT EXISTS idx_user_dept_dept_id ON sys_user_dept(dept_id);
CREATE INDEX IF NOT EXISTS idx_role_menu_role_id ON sys_role_menu(role_id);
CREATE INDEX IF NOT EXISTS idx_role_menu_menu_id ON sys_role_menu(menu_id);

-- 插入初始数据
-- 插入管理员角色
INSERT INTO sys_role (role_name, role_code, description, status, sort_order, created_by) 
VALUES ('系统管理员', 'ADMIN', '系统管理员，拥有所有权限', 1, 1, 'system')
ON CONFLICT (role_code) DO NOTHING;

-- 插入普通用户角色
INSERT INTO sys_role (role_name, role_code, description, status, sort_order, created_by) 
VALUES ('普通用户', 'USER', '普通用户，基础权限', 1, 2, 'system')
ON CONFLICT (role_code) DO NOTHING;

-- 插入管理员用户（密码：123456）
INSERT INTO sys_user (username, password, email, real_name, status, created_by) 
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'admin@example.com', '系统管理员', 1, 'system')
ON CONFLICT (username) DO NOTHING;

-- 为管理员用户分配管理员角色
INSERT INTO sys_user_role (user_id, role_id, created_by) 
SELECT u.id, r.id, 'system'
FROM sys_user u, sys_role r
WHERE u.username = 'admin' AND r.role_code = 'ADMIN'
ON CONFLICT (user_id, role_id) DO NOTHING;

-- 插入初始部门数据
INSERT INTO sys_dept (dept_name, dept_code, parent_id, ancestors, leader, status, sort_order, created_by) 
VALUES ('总公司', 'HQ', NULL, '0', 'admin', 1, 1, 'system')
ON CONFLICT (dept_code) DO NOTHING;

INSERT INTO sys_dept (dept_name, dept_code, parent_id, ancestors, leader, status, sort_order, created_by) 
VALUES ('技术部', 'TECH', 1, '0,1', 'admin', 1, 1, 'system')
ON CONFLICT (dept_code) DO NOTHING;

INSERT INTO sys_dept (dept_name, dept_code, parent_id, ancestors, leader, status, sort_order, created_by) 
VALUES ('人事部', 'HR', 1, '0,1', 'admin', 1, 2, 'system')
ON CONFLICT (dept_code) DO NOTHING;

-- 插入初始菜单数据
INSERT INTO sys_menu (menu_name, menu_code, parent_id, menu_type, path, component, perms, icon, visible, status, sort_order, created_by) 
VALUES ('系统管理', 'system', NULL, 'MENU', '/system', NULL, 'system:view', 'system', 1, 1, 1, 'system')
ON CONFLICT (menu_code) DO NOTHING;

INSERT INTO sys_menu (menu_name, menu_code, parent_id, menu_type, path, component, perms, icon, visible, status, sort_order, created_by) 
VALUES ('用户管理', 'system:user', 1, 'MENU', '/system/user', 'system/user/index', 'system:user:list', 'user', 1, 1, 1, 'system')
ON CONFLICT (menu_code) DO NOTHING;

INSERT INTO sys_menu (menu_name, menu_code, parent_id, menu_type, path, component, perms, icon, visible, status, sort_order, created_by) 
VALUES ('角色管理', 'system:role', 1, 'MENU', '/system/role', 'system/role/index', 'system:role:list', 'role', 1, 1, 2, 'system')
ON CONFLICT (menu_code) DO NOTHING;

INSERT INTO sys_menu (menu_name, menu_code, parent_id, menu_type, path, component, perms, icon, visible, status, sort_order, created_by) 
VALUES ('部门管理', 'system:dept', 1, 'MENU', '/system/dept', 'system/dept/index', 'system:dept:list', 'dept', 1, 1, 3, 'system')
ON CONFLICT (menu_code) DO NOTHING;

INSERT INTO sys_menu (menu_name, menu_code, parent_id, menu_type, path, component, perms, icon, visible, status, sort_order, created_by) 
VALUES ('菜单管理', 'system:menu', 1, 'MENU', '/system/menu', 'system/menu/index', 'system:menu:list', 'menu', 1, 1, 4, 'system')
ON CONFLICT (menu_code) DO NOTHING;

-- 为管理员角色分配所有菜单权限
INSERT INTO sys_role_menu (role_id, menu_id, created_by) 
SELECT r.id, m.id, 'system'
FROM sys_role r, sys_menu m
WHERE r.role_code = 'ADMIN'
ON CONFLICT (role_id, menu_id) DO NOTHING;

-- 为管理员用户分配总公司部门
INSERT INTO sys_user_dept (user_id, dept_id, is_primary, position, join_date, created_by) 
SELECT u.id, d.id, 1, '系统管理员', CURRENT_TIMESTAMP, 'system'
FROM sys_user u, sys_dept d
WHERE u.username = 'admin' AND d.dept_code = 'HQ'
ON CONFLICT (user_id, dept_id) DO NOTHING;
