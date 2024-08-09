<script lang="ts">
  import { createEventDispatcher } from "svelte";
  import type { ProjectService } from "../../projectService";
  import TagList from "../elements/TagList.svelte";

  export let id: any;
  export let service: ProjectService;

  $: if (id != null) {
    (async () => {
      updateEdit(await service.getProject(id));
    })();
  }
  function updateEdit(p) {
    name = p.name;
    content = p.content;
    tags = p.tags;
    editedDate = p.date;
  }

  let editedDate = "";

  let name = "";
  let content = "";

  let tags = [];
  let tagValue: string;

  const addTag = () => {
    if (tagValue.trim().length < 3) return;
    tags = [...new Set([...tags, tagValue.trim()])];
    tagValue = "";
  };

  const dispatch = createEventDispatcher();
  async function save() {
    if (id !== null) {
      console.log(tags);

      service.edit(id, name, tags.join(","), editedDate, content);
    } else {
      service.saveNew(
        name,
        tags.join(","),
        new Date().toISOString().split("T")[0],
        content
      );
    }
    dispatch("home", {});
  }

  let editArea: HTMLTextAreaElement;

  let width = 160;
  let height = 0;
  let streaming = false;
  let video = document.getElementById("video") as HTMLVideoElement;
  let canvas = document.getElementById("canvas") as HTMLCanvasElement;
  let photo = document.getElementById("photo");
  (async () => {
    const stream = await navigator.mediaDevices.getUserMedia({
      video: true,
      audio: false,
    });
    video.srcObject = stream;
    video.play();

    video.addEventListener(
      "canplay",
      (ev) => {
        if (!streaming) {
          height = (video.videoHeight / video.videoWidth) * width;

          video.setAttribute("width", width.toString());
          video.setAttribute("height", height.toString());
          canvas.setAttribute("width", width.toString());
          canvas.setAttribute("height", height.toString());
          streaming = true;
        }
      },
      false
    );
  })();
  function takepicture() {
    const context = canvas.getContext("2d");
    if (width && height) {
      canvas.width = width;
      canvas.height = height;
      context.drawImage(video, 0, 0, width, height);

      const data = canvas.toDataURL("image/png");
      editArea.value = editArea.value + `\n![foto z kamerki](${data})\n`;
    } else {
      clearphoto();
    }
  }
  function clearphoto() {
    const context = canvas.getContext("2d");
    context.fillStyle = "#AAA";
    context.fillRect(0, 0, canvas.width, canvas.height);

    const data = canvas.toDataURL("image/png");
    photo.setAttribute("src", data);
  }
</script>

<section>
  <div>
    <button on:click={() => dispatch("home", {})}>Cofnij</button>
    <h2 class="name">
      name: <input type="text" id="name" bind:value={name} />
    </h2>
    <h2>Kategorie:</h2>
    <span>Tags:</span>
    {#if tags.length > 0}
      <TagList {tags} />
    {:else}
      <br><span> No tags added</span><br>
    {/if}

    <input
      type="text"
      id="tag-add"
      bind:value={tagValue}
      on:keydown={(e) => {
        if (e.key === "Enter") addTag();
      }}
    />
    <h2>Zdjęcie z kamerki: <button on:click={takepicture}>Dodaj</button></h2>
  </div>
  <textarea id="edit-area" bind:this={editArea} bind:value={content} />
  <button on:click={save}>Zapis</button>
</section>

<style>
  section {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    align-items: stretch;

    color: var(--on-light);
    padding: 32px;
  }
  textarea {
    flex-grow: 1;
    resize: none;
  }
  .name {
    display: flex;
  }
  .name > input {
    flex-grow: 1;
  }
</style>
