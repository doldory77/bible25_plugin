//
//  CustomPlugin.m
//  Bible25Demo
//
//  Created by hyungsub kim on 2018. 7. 26..
//

#import "CustomPlugin.h"

@implementation CustomPlugin
- (void)share:(CDVInvokedUrlCommand *)command {
    CDVPluginResult *pluginResult = nil;
    NSString *text = [command.arguments objectAtIndex:0];
    
    if (text != nil && [text length] > 0) {
        NSArray *postItems = @[text];
        UIActivityViewController *activityVC = [[UIActivityViewController alloc] initWithActivityItems:postItems applicationActivities:nil];
        activityVC.excludedActivityTypes = @[UIActivityTypeAirDrop];
        
        [self.viewController presentViewController:activityVC animated:YES completion:nil];
        
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}
@end



/*

#import <Cordova/CDV.h>

@interface customPlugin : CDVPlugin {
  // Member variables go here.
}

- (void)coolMethod:(CDVInvokedUrlCommand*)command;
@end

@implementation customPlugin

- (void)coolMethod:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];

    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end

*/
