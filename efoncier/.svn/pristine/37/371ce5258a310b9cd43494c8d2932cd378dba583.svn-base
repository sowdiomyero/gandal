'use strict';

describe('Directive: fcPiedDePage', function () {

  // load the directive's module
  beforeEach(module('utilisateursApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<fc-pied-de-page></fc-pied-de-page>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the fcPiedDePage directive');
  }));
});
