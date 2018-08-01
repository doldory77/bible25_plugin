var exec = require('cordova/exec');

exports.func = function (className, methodName, argv, success, error) {
    exec(success, error, className, methodName, argv);
};
