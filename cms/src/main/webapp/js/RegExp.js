/**
 * Created by james on 2017/4/20.
 */
/**
 * 验证手机号
 * @param phone
 * @returns {boolean}
 */
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

/**
 * 验证密码
 * @param password
 * @returns {boolean}
 */
function isPassword(password) {
    var pattern = /^\d{6,}/;
    return pattern.test(password);
}

/**
 * 验证用户名
 * @param username
 * @returns {boolean}
 */
function isUsername(username) {
    var pattern = /^[a-zA-z]\w{3,15}$/;
    return pattern.test(username);
}

/**
 * 验证邮箱
 * @param email
 * @returns {boolean}
 */
function isEmail(email) {
    var pattern = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    return pattern.test(email);
}

/**
 * 验证等级
 * @param level
 * @returns {boolean}
 */
function isLevel(level) {
    var pattern = /^[123]$/;
    return pattern.test(level);
}