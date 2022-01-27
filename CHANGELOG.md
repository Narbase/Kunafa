## Change Log

### Kunafa 0.2.6 *(2019-11-24)*

- Added protected rootView to Component
- Added class `StringDimension` with DSl `st(dimension: String)` function

#### Bugfixes

- `onViewMounted` is called only when view is mounted in a mounted parent. If parent is not mounted, `onViewMounted` will not be called.

#### Breaking Changes

- Margin and Padding now accept `Dimension` instead of String.