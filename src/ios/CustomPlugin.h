//
//  CustomPlugin.h
//  Bible25Demo
//
//  Created by hyungsub kim on 2018. 7. 26..
//

#import <Cordova/CDVPlugin.h>
#import <UIKit/UIKit.h>

@interface CustomPlugin : CDVPlugin
- (void)share:(CDVInvokedUrlCommand *)command;
@end
