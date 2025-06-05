puppy-server
> a mini tomcat fork from [jerrymouse] (https://github.com/michaelliao/jerrymouse)

## structure
```text
  ┌───────────────────────────────┐
  │       Puppy Server            │
  │                 ┌───────────┐ │
  │  ┌─────────┐    │  Context  │ │
  │  │  HTTP   │    │┌─────────┐│ │
◀─┼─▶│Connector│◀──▶││ Web App ││ │
  │  └─────────┘    │└─────────┘│ │
  │                 └───────────┘ │
  └───────────────────────────────┘
```