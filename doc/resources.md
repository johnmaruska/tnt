# Loading Resources

All resources are expected to be .edn files describing everything
about the entity or category it's meant to represent. Anything
describing a category must be named `base.edn`. These bases are merged
in to every entity within its category. Entities can deviate from
categorical behavior by overwriting that field.

Resources are loaded based on directory structure. The highest
directory is which game the information is relevant for, with each
contained directory getting more specific.

```
armor
├── base.edn
└── light
    ├── base.edn
    ├── leather.edn
    ├── studded_leather.edn
    └── padded.edn
```

On the above example, when the `armor` directory is loaded in it will
load all entities with parent bases, meaning the leather, studded
leather, and padded entities will all merge over their `light` base
which will merge over the `armor` base. This happens recursively so
resources can have arbitrary depth. This also means that as you get
more specific in description, those fields are prioritized over the
base.

# Thinking about?

Maybe instead of merging directly have them make a nested structure of

```
{:category "armor"
 :base {}
 :children [ Category | Entity ]}
```

and apply the base as-needed. This would reduce lots of duplication
across entities. Requiring the entire entity be passed around is
already a thing in the current implementation.
