<script lang="ts">
  import { createEventDispatcher } from "svelte";
  import TagList from "../elements/TagList.svelte";
  import SvelteMarkdown from "svelte-markdown";

  export let projectData: {
    name: string;
    dodane: string;
    date: string;
    tags: string[];
    content: string;
    rowid: Number;
  };
  let { name, dodane, date, tags, content, rowid } = projectData;
  const dispatch = createEventDispatcher();
</script>

<section>
  <div>
    <button on:click={() => dispatch("back", {})}>Cofnij</button>
    <button on:click={() => dispatch("edit", { rowid })}>Edytuj</button>
    <button on:click={() => window.print()}>Drukuj</button>
    <button on:click={() => dispatch("delete", { rowid })}>Usuń</button>
  </div>
  <h1>{name}</h1>
  <span class="attr">Data: {date}</span>
  <TagList {tags} />
  <div class="listcon">
    <SvelteMarkdown source={content} />
  </div>
</section>

<style>
  section {
    flex-grow: 1;
    display: flex;
    flex-direction: column;

    color: var(--on-light);
    padding: 26px;
  }
  section > * {
    padding: 8px;
  }
  h1 {
    margin: 0;
    padding-bottom: 8px;
    font-size: 2rem;
  }

  .listcon {
    overflow-y: scroll;
    flex-grow: 1;
    flex-basis: 0;
    border: 1px solid gray;
    color: black;
  }
  .listcon::-webkit-scrollbar {
    height: 12px;
    width: 12px;
    background: #f3f0f0;
  }

  .listcon::-webkit-scrollbar-thumb {
    background: var(--dark);
    -webkit-border-radius: 1ex;
  }

  .listcon::-webkit-scrollbar-corner {
    background: #fff;
  }
</style>
