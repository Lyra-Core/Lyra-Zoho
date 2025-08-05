Pod::Spec.new do |s|
  s.name         = 'LyraZoho'
  s.version      = '1.0.0'
  s.summary      = 'Lyra-Zoho SDK for iOS integration'
  s.description  = <<-DESC
                   A comprehensive SDK for integrating Lyra-Zoho services into iOS applications.
                   Provides seamless access to Zoho APIs with native Swift implementation.
                   DESC
  s.homepage     = 'https://github.com/Lyra-Core/Lyra-Zoho'
  s.license      = { :type => 'MIT', :file => 'LICENSE' }
  s.author       = { 'Lyra Core' => 'support@lyracore.com' }
  s.source       = { :git => 'https://github.com/Lyra-Core/Lyra-Zoho.git', :tag => s.version.to_s }
  
  s.ios.deployment_target = '15.0'
  s.swift_version = '5.9'
  
  s.source_files = 'ios/Sources/LyraZoho/**/*.{swift,h,m}'
  s.public_header_files = 'ios/Sources/LyraZoho/**/*.h'
  
  # Dependencies for Zoho API integration
  # Uncomment and adjust versions as needed for your specific requirements
    # Dependencies - uncomment and modify as needed for your Zoho SDK
  # s.dependency 'Alamofire', '~> 5.8'           # For HTTP networking
  
  # Framework settings
  s.frameworks = 'Foundation', 'UIKit', 'Security'
  s.requires_arc = true
end