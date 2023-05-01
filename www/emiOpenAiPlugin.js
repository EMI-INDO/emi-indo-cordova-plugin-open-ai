var exec = require('cordova/exec');

exports.completions = function (arg0, arg1, arg2, arg3, arg4, success, error) {
    exec(success, error, 'emiOpenAiPlugin', 'completions', [arg0, arg1, arg2, arg3, arg4]);
};

exports.chat = function (arg0, arg1, arg2, arg3, success, error) {
    exec(success, error, 'emiOpenAiPlugin', 'chat', [arg0, arg1, arg2, arg3]);
};
