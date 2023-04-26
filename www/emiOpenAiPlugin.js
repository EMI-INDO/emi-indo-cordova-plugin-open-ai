var exec = require('cordova/exec');

exports.completions = function (arg0, arg1, success, error) {
    exec(success, error, 'emiOpenAiPlugin', 'completions', [arg0, arg1]);
};
