'use strict';

describe('Directive: fcEntete', function () {

  // load the directive's module
  beforeEach(module('utilisateursApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<fc-entete></fc-entete>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the fcEntete directive');
  }));
});
