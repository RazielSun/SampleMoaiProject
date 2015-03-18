#----------------------------------------------------------------
# Generated CMake target import file for configuration "Release".
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Import target "cpufeatures" for configuration "Release"
set_property(TARGET cpufeatures APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(cpufeatures PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libcpufeatures.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS cpufeatures )
list(APPEND _IMPORT_CHECK_FILES_FOR_cpufeatures "${_IMPORT_PREFIX}/lib/libcpufeatures.a" )

# Import target "box2d" for configuration "Release"
set_property(TARGET box2d APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(box2d PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libbox2d.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS box2d )
list(APPEND _IMPORT_CHECK_FILES_FOR_box2d "${_IMPORT_PREFIX}/lib/libbox2d.a" )

# Import target "contrib" for configuration "Release"
set_property(TARGET contrib APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(contrib PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libcontrib.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS contrib )
list(APPEND _IMPORT_CHECK_FILES_FOR_contrib "${_IMPORT_PREFIX}/lib/libcontrib.a" )

# Import target "crypto" for configuration "Release"
set_property(TARGET crypto APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(crypto PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libcrypto.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS crypto )
list(APPEND _IMPORT_CHECK_FILES_FOR_crypto "${_IMPORT_PREFIX}/lib/libcrypto.a" )

# Import target "curl" for configuration "Release"
set_property(TARGET curl APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(curl PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "zlib;cares;ssl"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libcurl.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS curl )
list(APPEND _IMPORT_CHECK_FILES_FOR_curl "${_IMPORT_PREFIX}/lib/libcurl.a" )

# Import target "cares" for configuration "Release"
set_property(TARGET cares APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(cares PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libcares.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS cares )
list(APPEND _IMPORT_CHECK_FILES_FOR_cares "${_IMPORT_PREFIX}/lib/libcares.a" )

# Import target "expat" for configuration "Release"
set_property(TARGET expat APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(expat PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libexpat.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS expat )
list(APPEND _IMPORT_CHECK_FILES_FOR_expat "${_IMPORT_PREFIX}/lib/libexpat.a" )

# Import target "freetype" for configuration "Release"
set_property(TARGET freetype APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(freetype PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "zlib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libfreetype.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS freetype )
list(APPEND _IMPORT_CHECK_FILES_FOR_freetype "${_IMPORT_PREFIX}/lib/libfreetype.a" )

# Import target "jansson" for configuration "Release"
set_property(TARGET jansson APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(jansson PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libjansson.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS jansson )
list(APPEND _IMPORT_CHECK_FILES_FOR_jansson "${_IMPORT_PREFIX}/lib/libjansson.a" )

# Import target "jpg" for configuration "Release"
set_property(TARGET jpg APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(jpg PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libjpg.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS jpg )
list(APPEND _IMPORT_CHECK_FILES_FOR_jpg "${_IMPORT_PREFIX}/lib/libjpg.a" )

# Import target "liblua-static" for configuration "Release"
set_property(TARGET liblua-static APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(liblua-static PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libliblua-static.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS liblua-static )
list(APPEND _IMPORT_CHECK_FILES_FOR_liblua-static "${_IMPORT_PREFIX}/lib/libliblua-static.a" )

# Import target "luafilesystem" for configuration "Release"
set_property(TARGET luafilesystem APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(luafilesystem PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libluafilesystem.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS luafilesystem )
list(APPEND _IMPORT_CHECK_FILES_FOR_luafilesystem "${_IMPORT_PREFIX}/lib/libluafilesystem.a" )

# Import target "luasql" for configuration "Release"
set_property(TARGET luasql APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(luasql PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "sqlite3;liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libluasql.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS luasql )
list(APPEND _IMPORT_CHECK_FILES_FOR_luasql "${_IMPORT_PREFIX}/lib/libluasql.a" )

# Import target "luacurl" for configuration "Release"
set_property(TARGET luacurl APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(luacurl PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "curl;liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libluacurl.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS luacurl )
list(APPEND _IMPORT_CHECK_FILES_FOR_luacurl "${_IMPORT_PREFIX}/lib/libluacurl.a" )

# Import target "luacrypto" for configuration "Release"
set_property(TARGET luacrypto APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(luacrypto PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "crypto;liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libluacrypto.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS luacrypto )
list(APPEND _IMPORT_CHECK_FILES_FOR_luacrypto "${_IMPORT_PREFIX}/lib/libluacrypto.a" )

# Import target "luasocket" for configuration "Release"
set_property(TARGET luasocket APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(luasocket PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libluasocket.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS luasocket )
list(APPEND _IMPORT_CHECK_FILES_FOR_luasocket "${_IMPORT_PREFIX}/lib/libluasocket.a" )

# Import target "mongoose" for configuration "Release"
set_property(TARGET mongoose APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(mongoose PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmongoose.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS mongoose )
list(APPEND _IMPORT_CHECK_FILES_FOR_mongoose "${_IMPORT_PREFIX}/lib/libmongoose.a" )

# Import target "ogg" for configuration "Release"
set_property(TARGET ogg APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(ogg PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libogg.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS ogg )
list(APPEND _IMPORT_CHECK_FILES_FOR_ogg "${_IMPORT_PREFIX}/lib/libogg.a" )

# Import target "png" for configuration "Release"
set_property(TARGET png APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(png PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "zlib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libpng.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS png )
list(APPEND _IMPORT_CHECK_FILES_FOR_png "${_IMPORT_PREFIX}/lib/libpng.a" )

# Import target "sfmt" for configuration "Release"
set_property(TARGET sfmt APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(sfmt PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libsfmt.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS sfmt )
list(APPEND _IMPORT_CHECK_FILES_FOR_sfmt "${_IMPORT_PREFIX}/lib/libsfmt.a" )

# Import target "sqlite3" for configuration "Release"
set_property(TARGET sqlite3 APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(sqlite3 PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libsqlite3.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS sqlite3 )
list(APPEND _IMPORT_CHECK_FILES_FOR_sqlite3 "${_IMPORT_PREFIX}/lib/libsqlite3.a" )

# Import target "spine" for configuration "Release"
set_property(TARGET spine APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(spine PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libspine.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS spine )
list(APPEND _IMPORT_CHECK_FILES_FOR_spine "${_IMPORT_PREFIX}/lib/libspine.a" )

# Import target "ssl" for configuration "Release"
set_property(TARGET ssl APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(ssl PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "crypto"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libssl.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS ssl )
list(APPEND _IMPORT_CHECK_FILES_FOR_ssl "${_IMPORT_PREFIX}/lib/libssl.a" )

# Import target "tess" for configuration "Release"
set_property(TARGET tess APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(tess PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libtess.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS tess )
list(APPEND _IMPORT_CHECK_FILES_FOR_tess "${_IMPORT_PREFIX}/lib/libtess.a" )

# Import target "tinyxml" for configuration "Release"
set_property(TARGET tinyxml APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(tinyxml PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libtinyxml.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS tinyxml )
list(APPEND _IMPORT_CHECK_FILES_FOR_tinyxml "${_IMPORT_PREFIX}/lib/libtinyxml.a" )

# Import target "tlsf" for configuration "Release"
set_property(TARGET tlsf APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(tlsf PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libtlsf.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS tlsf )
list(APPEND _IMPORT_CHECK_FILES_FOR_tlsf "${_IMPORT_PREFIX}/lib/libtlsf.a" )

# Import target "vorbis" for configuration "Release"
set_property(TARGET vorbis APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(vorbis PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "ogg"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libvorbis.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS vorbis )
list(APPEND _IMPORT_CHECK_FILES_FOR_vorbis "${_IMPORT_PREFIX}/lib/libvorbis.a" )

# Import target "webp" for configuration "Release"
set_property(TARGET webp APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(webp PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "cpufeatures"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libwebp.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS webp )
list(APPEND _IMPORT_CHECK_FILES_FOR_webp "${_IMPORT_PREFIX}/lib/libwebp.a" )

# Import target "zlib" for configuration "Release"
set_property(TARGET zlib APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(zlib PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "C"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libzlib.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS zlib )
list(APPEND _IMPORT_CHECK_FILES_FOR_zlib "${_IMPORT_PREFIX}/lib/libzlib.a" )

# Import target "untz" for configuration "Release"
set_property(TARGET untz APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(untz PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "vorbis"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libuntz.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS untz )
list(APPEND _IMPORT_CHECK_FILES_FOR_untz "${_IMPORT_PREFIX}/lib/libuntz.a" )

# Import target "zlvfs" for configuration "Release"
set_property(TARGET zlvfs APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(zlvfs PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "zlib;tlsf"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libzlvfs.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS zlvfs )
list(APPEND _IMPORT_CHECK_FILES_FOR_zlvfs "${_IMPORT_PREFIX}/lib/libzlvfs.a" )

# Import target "zlcrypto" for configuration "Release"
set_property(TARGET zlcrypto APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(zlcrypto PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "crypto;zlcore"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libzlcrypto.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS zlcrypto )
list(APPEND _IMPORT_CHECK_FILES_FOR_zlcrypto "${_IMPORT_PREFIX}/lib/libzlcrypto.a" )

# Import target "zlcore" for configuration "Release"
set_property(TARGET zlcore APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(zlcore PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "expat;zlvfs;zlib"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libzlcore.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS zlcore )
list(APPEND _IMPORT_CHECK_FILES_FOR_zlcore "${_IMPORT_PREFIX}/lib/libzlcore.a" )

# Import target "moai-core" for configuration "Release"
set_property(TARGET moai-core APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-core PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "zlcore;liblua-static"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-core.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-core )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-core "${_IMPORT_PREFIX}/lib/libmoai-core.a" )

# Import target "moai-util" for configuration "Release"
set_property(TARGET moai-util APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-util PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-core;zlcore;sfmt;jansson;tinyxml;ssl"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-util.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-util )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-util "${_IMPORT_PREFIX}/lib/libmoai-util.a" )

# Import target "moai-sim" for configuration "Release"
set_property(TARGET moai-sim APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-sim PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-core;moai-util;contrib;zlcore;tess;freetype;png;jpg;webp;moai-box2d"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-sim.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-sim )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-sim "${_IMPORT_PREFIX}/lib/libmoai-sim.a" )

# Import target "moai-crypto" for configuration "Release"
set_property(TARGET moai-crypto APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-crypto PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core;zlcrypto;zlcore"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-crypto.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-crypto )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-crypto "${_IMPORT_PREFIX}/lib/libmoai-crypto.a" )

# Import target "moai-box2d" for configuration "Release"
set_property(TARGET moai-box2d APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-box2d PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-core;moai-util;box2d;zlcore"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-box2d.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-box2d )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-box2d "${_IMPORT_PREFIX}/lib/libmoai-box2d.a" )

# Import target "moai-untz" for configuration "Release"
set_property(TARGET moai-untz APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-untz PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-core;moai-util;untz"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-untz.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-untz )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-untz "${_IMPORT_PREFIX}/lib/libmoai-untz.a" )

# Import target "moai-fmod-studio" for configuration "Release"
set_property(TARGET moai-fmod-studio APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-fmod-studio PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-core;moai-util;libfmod"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-fmod-studio.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-fmod-studio )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-fmod-studio "${_IMPORT_PREFIX}/lib/libmoai-fmod-studio.a" )

# Import target "moai-http-client" for configuration "Release"
set_property(TARGET moai-http-client APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-http-client PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-core;moai-util;curl"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-http-client.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-http-client )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-http-client "${_IMPORT_PREFIX}/lib/libmoai-http-client.a" )

# Import target "moai-luaext" for configuration "Release"
set_property(TARGET moai-luaext APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-luaext PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-core;luasocket;luafilesystem;luacurl;luacrypto;luasql"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-luaext.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-luaext )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-luaext "${_IMPORT_PREFIX}/lib/libmoai-luaext.a" )

# Import target "moai-audiosampler" for configuration "Release"
set_property(TARGET moai-audiosampler APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-audiosampler PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-core;moai-util;moai-sim;zlcore"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-audiosampler.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-audiosampler )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-audiosampler "${_IMPORT_PREFIX}/lib/libmoai-audiosampler.a" )

# Import target "moai-spine" for configuration "Release"
set_property(TARGET moai-spine APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-spine PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-core;moai-util;spine;zlcore"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-spine.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-spine )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-spine "${_IMPORT_PREFIX}/lib/libmoai-spine.a" )

# Import target "moai-android-adcolony" for configuration "Release"
set_property(TARGET moai-android-adcolony APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-adcolony PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-adcolony.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-adcolony )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-adcolony "${_IMPORT_PREFIX}/lib/libmoai-android-adcolony.a" )

# Import target "moai-android-chartboost" for configuration "Release"
set_property(TARGET moai-android-chartboost APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-chartboost PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-chartboost.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-chartboost )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-chartboost "${_IMPORT_PREFIX}/lib/libmoai-android-chartboost.a" )

# Import target "moai-android-crittercism" for configuration "Release"
set_property(TARGET moai-android-crittercism APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-crittercism PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-crittercism.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-crittercism )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-crittercism "${_IMPORT_PREFIX}/lib/libmoai-android-crittercism.a" )

# Import target "moai-android-facebook" for configuration "Release"
set_property(TARGET moai-android-facebook APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-facebook PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-facebook.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-facebook )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-facebook "${_IMPORT_PREFIX}/lib/libmoai-android-facebook.a" )

# Import target "moai-android-flurry" for configuration "Release"
set_property(TARGET moai-android-flurry APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-flurry PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-flurry.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-flurry )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-flurry "${_IMPORT_PREFIX}/lib/libmoai-android-flurry.a" )

# Import target "moai-android-flurry-ads" for configuration "Release"
set_property(TARGET moai-android-flurry-ads APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-flurry-ads PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-flurry-ads.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-flurry-ads )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-flurry-ads "${_IMPORT_PREFIX}/lib/libmoai-android-flurry-ads.a" )

# Import target "moai-android-google-analytics" for configuration "Release"
set_property(TARGET moai-android-google-analytics APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-google-analytics PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-google-analytics.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-google-analytics )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-google-analytics "${_IMPORT_PREFIX}/lib/libmoai-android-google-analytics.a" )

# Import target "moai-android-google-play-services" for configuration "Release"
set_property(TARGET moai-android-google-play-services APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-google-play-services PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-google-play-services.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-google-play-services )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-google-play-services "${_IMPORT_PREFIX}/lib/libmoai-android-google-play-services.a" )

# Import target "moai-android-google-play-services-games" for configuration "Release"
set_property(TARGET moai-android-google-play-services-games APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-google-play-services-games PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-google-play-services-games.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-google-play-services-games )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-google-play-services-games "${_IMPORT_PREFIX}/lib/libmoai-android-google-play-services-games.a" )

# Import target "moai-android-tapjoy" for configuration "Release"
set_property(TARGET moai-android-tapjoy APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-tapjoy PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-tapjoy.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-tapjoy )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-tapjoy "${_IMPORT_PREFIX}/lib/libmoai-android-tapjoy.a" )

# Import target "moai-android-twitter" for configuration "Release"
set_property(TARGET moai-android-twitter APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-twitter PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-twitter.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-twitter )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-twitter "${_IMPORT_PREFIX}/lib/libmoai-android-twitter.a" )

# Import target "moai-android-vungle" for configuration "Release"
set_property(TARGET moai-android-vungle APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android-vungle PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android-vungle.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android-vungle )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android-vungle "${_IMPORT_PREFIX}/lib/libmoai-android-vungle.a" )

# Import target "moai-android" for configuration "Release"
set_property(TARGET moai-android APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)
set_target_properties(moai-android PROPERTIES
  IMPORTED_LINK_INTERFACE_LANGUAGES_RELEASE "CXX"
  IMPORTED_LINK_INTERFACE_LIBRARIES_RELEASE "moai-sim;moai-util;moai-core"
  IMPORTED_LOCATION_RELEASE "${_IMPORT_PREFIX}/lib/libmoai-android.a"
  )

list(APPEND _IMPORT_CHECK_TARGETS moai-android )
list(APPEND _IMPORT_CHECK_FILES_FOR_moai-android "${_IMPORT_PREFIX}/lib/libmoai-android.a" )

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)
