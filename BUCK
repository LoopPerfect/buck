genrule(
  name = 'debian',
  out = 'out',
  srcs = [
    'debian/Changelog',
    'debian/buck.equivs',
    'LICENSE',
    'README.md'
  ],
  cmd = ' && '.join([
    'mkdir -p $OUT',
    'cp $SRCS $OUT',
    'cp $(location //programs:buck) $OUT',
    'cp $(location //programs:buckd) $OUT',
    'cp $(location //programs:gen_buck_info) $OUT',
    'cd $OUT',
    'mv buck.pex buck',
    'mv buckd.pex buckd',
    'mv gen_buck_info.pex gen_buck_info',
    'equivs-build buck.equivs'
  ])
)
